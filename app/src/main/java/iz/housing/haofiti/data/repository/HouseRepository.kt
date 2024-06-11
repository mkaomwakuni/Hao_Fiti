package iz.housing.haofiti.data.repository
import iz.housing.haofiti.data.model.HouseDataState
import kotlinx.coroutines.flow.Flow

interface HouseRepository {
    fun fetchHouses(): Flow<HouseDataState>
}


