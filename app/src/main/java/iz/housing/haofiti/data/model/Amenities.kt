package iz.housing.haofiti.data.model

data class Amenities(
    val bedrooms: Int? = 0,
    val bathrooms: Int? = 0,
    val wifi: Boolean? = null,
    val ac: Boolean? = null,
    val parking: Boolean? = null,
    val netflix: Boolean? = null,
    val security: Boolean? = null,
    val coveredArea: Int? = 0,
    val condition: String? = "",
    val floor: Int? = 0,
    val yoc:Int? = 0,
    val plotArea:Int? = 0,
    val furnished: String ="",
    val verandaCovered: Int? = 0
)