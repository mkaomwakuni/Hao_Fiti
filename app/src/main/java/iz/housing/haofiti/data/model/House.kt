package iz.housing.haofiti.data.model

import androidx.room.Entity

data class Housing(
    val locations: Map<String, Location> = emptyMap()
)

data class Location(
    val properties: Map<String, Property> = emptyMap()
)

data class Property(
    val apartments: Map<String, PropertyItem> = emptyMap(),
    val villas: Map<String, PropertyItem> = emptyMap(),
    val bungalows: Map<String, PropertyItem> = emptyMap(),
    val rentals: Map<String, PropertyItem> = emptyMap()
)

@Entity(tableName = "Houses")
data class PropertyItem(
    val agent: Agent? = null,
    val description: String = "",
    val id: Int = 0,
    val images: List<String> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val location: String = "",
    val name: String = "",
    val type: PropertyType = PropertyType.APARTMENT,
    val amenities:Map<String, Any>? = null,
    val rating: Double? = null
)

enum class PropertyType {
    APARTMENT, VILLA, BUNGALOW, RENTAL
}