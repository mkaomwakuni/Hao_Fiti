package iz.housing.haofiti.data.model

import iz.housing.haofiti.data.database.HouseInfo

sealed class HouseDataState{
    class success(val data: MutableList<HouseInfo>) : HouseDataState()
    class Failure(val message: String) : HouseDataState()
    object Loading : HouseDataState()
    object Empty: HouseDataState()
}
