package iz.housing.haofiti.data.model

data class Housing(
    val properties: List<PropertyCategory>
)

data class PropertyCategory(
    val category: String,
    val properties: List<Property>
)

data class Property(
    val id: Int,
    val name: String,
    val location: String,
    val rating: Double,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    val images: List<String>,
    val amenities: Amenities,
    val agent: Agent
)
