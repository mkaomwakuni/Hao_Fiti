package iz.housing.haofiti.data.model

data class Amenities(
    val bedrooms: Int? = 0,
    val bathrooms: Int? = 0,
    val wifi: Boolean? = null,
    val ac: Boolean? = null,
    val parking: Boolean? = null,
    val netflix: Boolean? = null,
    val security: Boolean? = null,
    val hotshowers: Boolean? = null
)