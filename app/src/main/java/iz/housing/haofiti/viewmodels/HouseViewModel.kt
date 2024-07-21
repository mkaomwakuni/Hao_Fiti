package iz.housing.haofiti.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.PropertyType
import iz.housing.haofiti.data.repository.HouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HouseViewModel @Inject constructor(private val repository: HouseRepository) : ViewModel() {

    private val _properties = MutableStateFlow<List<PropertyItem>>(emptyList())
    val properties: StateFlow<List<PropertyItem>> = _properties.asStateFlow()

    private val _selectedProperty = MutableStateFlow<PropertyItem?>(null)
    val selectedProperty: StateFlow<PropertyItem?> = _selectedProperty.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String?>(null)
    val selectedLocation: StateFlow<String?> = _selectedLocation.asStateFlow()

    private val _allLocations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _allLocations.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllProperties().collect { property ->
                _properties.value = property
            }
        }
    }

    fun selectLocation(location: String) {
        _selectedLocation.value = location
        viewModelScope.launch {
            repository.getPropertiesByLocation(location).collect { propertyList ->
                _properties.value = propertyList
            }
        }
    }

    fun getPropertiesByType(type: PropertyType): List<PropertyItem> {
        return properties.value.filter { it.type == type }
    }
}