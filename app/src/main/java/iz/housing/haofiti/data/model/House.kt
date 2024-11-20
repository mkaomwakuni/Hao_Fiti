package iz.housing.haofiti.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Houses")
data class PropertyItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    val energyEfficiencyIndex: Int = 0
)

data class Location(
    val locationName: String = "",
    val properties: List<PropertyItem> = emptyList()
)

data class Towns(
    val name: String = "",
    val imagesRes: Int
)

enum class PropertyType {
    APARTMENT, VILLA, BUNGALOW, RENTAL
}