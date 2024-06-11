package iz.housing.haofiti.data.database

data class HouseInfo(
    val name: String = "",
    val images: List<String> = listOf(),
    val noOfBedrooms: Int = 0,
    val noOfBathrooms: Int = 0,
    val rentalAmount: Int = 0,
    val rating: Double = 0.0,
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val description: String = "",
    val agentName: String = "",
    val agentImage: String = ""
)
