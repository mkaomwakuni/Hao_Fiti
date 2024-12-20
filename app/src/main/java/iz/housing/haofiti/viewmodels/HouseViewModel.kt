package iz.housing.haofiti.viewmodels
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.ThemePreferencesManager
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.PropertyType
import iz.housing.haofiti.data.repository.HouseRepository
import iz.housing.haofiti.data.service.HouseEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HouseRepository,
    private val themePreferencesManager: ThemePreferencesManager
) : ViewModel() {

    companion object {
        private const val TAG = "HouseViewModel"
    }

    val isDarkMode = themePreferencesManager.isDarkMode
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    private var _uiState = MutableStateFlow(HouseStates())
    val uiState: StateFlow<HouseStates> = _uiState.asStateFlow()

    private val _locationName = MutableStateFlow("Urban")
    val locationName: StateFlow<String> = _locationName.asStateFlow()

    private val _savedPropertyListings = MutableStateFlow<List<PropertyItem>>(emptyList())
    val savedPropertyListings: StateFlow<List<PropertyItem>> = _savedPropertyListings.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadSavedProperties()
        loadInitialData()
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            themePreferencesManager.setDarkMode(enabled)
        }
    }

    fun fetchCurrentLocation(context: Context) {
        viewModelScope.launch {
            val locationResult = repository.getCurrentLocation(context)
            Log.d("HouseViewModel", "Location Result: $locationResult")

            if (locationResult is ResponseUtil.Success) {
                _locationName.value = locationResult.data.toString()
                _uiState.update {
                    it.copy(currentLocation = _locationName.value)
                }
                Log.d("HouseViewModel", "Updated Location Name: ${_locationName.value}")
            } else {
                _locationName.value = "Urban"
                _uiState.update { it.copy(currentLocation = "Urban") }
                Log.e("HouseViewModel", "Error fetching location: ${locationResult.message}")
            }
        }
    }



    fun loadInitialData() {
        Log.d(TAG, "Loading initial data")
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getProperties()) {
                is ResponseUtil.Success -> {
                    Log.d(TAG, "Fetched properties successfully")
                    result.data?.let { properties ->
                        _uiState.update {
                            it.copy(
                                properties = properties,
                                filteredProperties = properties,
                                isLoading = false
                            )
                        }
                    }
                }
                is ResponseUtil.Error -> {
                    Log.e(TAG, "Error loading data: ${result.message}")
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
        } catch (e: DatabaseException) {
            Log.e("HouseViewModel", "Firebase error: ${e.message}")
            ResponseUtil.Error("Permission denied. Check Firebase rules and user authentication.")
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
                loadSavedProperties()
            } else {
                repository.deleteHome(saveProperty)
                loadSavedProperties()
            }
        }
    }

    private fun loadSavedProperties() {
        viewModelScope.launch {
            when (val result = repository.getSavedHomes()) {
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

    fun getPropertyById(id: String): PropertyItem? {
        return uiState.value.properties.find { it.name == id }
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