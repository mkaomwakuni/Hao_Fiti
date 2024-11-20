package iz.housing.haofiti.data.model

data class HouseStates(
    val properties: List<PropertyItem> = emptyList(),
    val filteredProperties: List<PropertyItem> = emptyList(),
    val selectedPropertyId: PropertyItem? = null,
    val selectedLocation: String? = null,
    val allLocations: List<Location> = emptyList(),
    val searchQuery: String = "",
    val propertyCategory: PropertyType? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentLocation: String? = "urban",
    val isLocationPermissionGranted: Boolean = false
)