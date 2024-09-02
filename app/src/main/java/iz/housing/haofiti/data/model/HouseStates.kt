package iz.housing.haofiti.data.model

data class HouseStates(
    val properties: List<PropertyItem> = emptyList(),       // All properties loaded initially
    val filteredProperties: List<PropertyItem> = emptyList(), // Properties filtered based on search or category
    val selectedPropertyId: PropertyItem? = null,                    // ID of the currently selected property
    val selectedLocation: String? = null,                    // Location currently selected for filtering
    val allLocations: List<Location> = emptyList(),            // List of all unique locations
    val searchQuery: String = "",                            // Current search query string
    val propertyCategory: PropertyType? = null,              // Selected property category for filtering
    val isLoading: Boolean = false,                          // Loading state
    val error: String? = null,                               // Error message if any
)