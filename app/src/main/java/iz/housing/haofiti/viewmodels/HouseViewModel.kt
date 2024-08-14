package iz.housing.haofiti.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.PropertyType
import iz.housing.haofiti.data.repository.HouseRepository
import iz.housing.haofiti.data.service.HouseEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(private val repository: HouseRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(HouseStates())
    val uiState: StateFlow<HouseStates> = _uiState.asStateFlow()

    private val _savedPropertyListings = MutableStateFlow<List<PropertyItem>>(emptyList())
    val savedPropertyListings: StateFlow<List<PropertyItem>> = _savedPropertyListings.asStateFlow()
    private var searchJob: Job? = null

    init {
        loadSavedProperties()
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getProperties()) {
                is ResponseUtil.Success -> {
                    result.data?.let { properties ->
                        val locations = properties.map { it.location }.distinct()
                        _uiState.update {
                            it.copy(
                                properties = properties,
                                filteredProperties = properties,  // Initially, all properties are shown
                                isLoading = false
                            )
                        }
                    }
                }
                is ResponseUtil.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "An error occurred while fetching data", isLoading = false)
                    }
                }
            }
        }
    }

    private suspend fun getProperties(): ResponseUtil<List<PropertyItem>> {
        return try {
            val properties = repository.getAllProperties().first()
            ResponseUtil.Success(properties)
        } catch (e: Exception) {
            ResponseUtil.Error(e.message ?: "An error occurred while fetching data")
        }
    }

    fun selectLocation(location: String) {
        viewModelScope.launch {
            try {
                val propertiesInLocation = uiState.value.properties.filter { it.location == location }
                _uiState.update {
                    it.copy(
                        selectedLocation = location,
                        filteredProperties = propertiesInLocation
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: "An error occurred while loading properties for $location")
                }
            }
        }
    }

    fun saveProperty(property: PropertyItem) {
        viewModelScope.launch {
            val saveProperty = property.copy(isSaved = !property.isSaved)
            if (saveProperty.isSaved) {
                repository.insertHomes(saveProperty)
                loadSavedProperties()  // Ensure the saved properties are reloaded
                Log.d("HouseViewModel", " bookmarked: ${saveProperty.name}")
            } else {
                repository.deleteHome(saveProperty)
                loadSavedProperties()  // Ensure the saved properties are reloaded
                Log.d("HouseViewModel", " removed: ${saveProperty.name}")
            }
        }
    }

    private fun loadSavedProperties() {
        viewModelScope.launch {
            when(val result = repository.getSavedHomes()){
                is ResponseUtil.Success -> {
                    result.data?.let {
                        _savedPropertyListings.value = it
                    }
                }
                is ResponseUtil.Error -> {
                    _savedPropertyListings.value = emptyList()
                }
            }
        }
    }

    private fun getPropertiesByType(type: PropertyType): List<PropertyItem> {
        return uiState.value.properties.filter { it.type == type }
    }

    fun getPropertyById(id: Int): PropertyItem? {
        return uiState.value.properties.find { it.id == id }
    }

    fun onEvent(event: HouseEvent) {
        when (event) {
            is HouseEvent.SearchBarClicked -> handleSearchBarClick(event.searchQuery)
            is HouseEvent.OnCardClicked -> handleCardClick(event.property)
            is HouseEvent.OnCategoryClicked -> handleCategoryClick(event.category)
        }
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchBarClick(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            flow { emit(query) }
                .debounce(1000)
                .collect { query ->
                    searchProperty(query)
                }
        }
    }

    private fun handleCardClick(property: PropertyItem) {
        _uiState.update {
            it.copy(selectedPropertyId = property)
        }
    }

    private fun handleCategoryClick(category: PropertyType) {
        _uiState.update {
            it.copy(
                propertyCategory = category,
                filteredProperties = getPropertiesByType(category)
            )
        }
    }

    private fun searchProperty(query: String) {
        if (query.isEmpty()) {
            loadInitialData()
        } else {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                val result = repository.searchQuery(query = query)
                try {
                    val searchResults = repository.searchQuery(query).first()
                    _uiState.update {
                        it.copy(
                            filteredProperties = searchResults,
                            isLoading = false,
                            error = null
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            error = e.message ?: "An error occurred during search",
                            isLoading = false,
                            filteredProperties = emptyList()
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}