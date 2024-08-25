package iz.housing.haofiti.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Houses")
data class PropertyItem(
    @PrimaryKey val id: Int = 0,
    val price: Int = 0,
    val isSaved: Boolean = false,
    val agent: Agent? = null,
    val description: String = "",
    val images: List<String> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val location: String = "",
    val name: String = "",
    val type: PropertyType = PropertyType.APARTMENT,
    val amenities: Amenities? = null,
    val rating: Double? = null,
    val imageRes: Int? = null
)

data class Housing(
    val locations: List<Location> = emptyList()
)

data class Location(
    val locationName: String = "",
    val properties: List<PropertyItem> = emptyList()
)

enum class PropertyType {
    APARTMENT, VILLA, BUNGALOW, RENTAL
}
