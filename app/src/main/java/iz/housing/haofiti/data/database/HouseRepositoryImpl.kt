package iz.housing.haofiti.data.database

import iz.housing.haofiti.data.FirebaseDataProvider
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.repository.HouseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HouseRepositoryImpl  @Inject constructor(
    private  val houseDao: HouseDao,
    private val firebaseDataProvider: FirebaseDataProvider
) : HouseRepository {

    override suspend fun getAllProperties(): Flow<List<PropertyItem>> = firebaseDataProvider.getAllProperties()

    override suspend fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>> = firebaseDataProvider.getPropertyByLocation(location)

    override suspend fun getAvailableLocations(): Flow<List<String>> = firebaseDataProvider.getAllLocations()

    override suspend fun searchQuery(query: String): Flow<List<PropertyItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedHomes(): List<PropertyItem> = houseDao.getSavedHouses()

    override suspend fun insertHomes(house: PropertyItem) {
        houseDao.insertHome(house)
    }

    override suspend fun deleteHome(house: PropertyItem) {
        houseDao.deleteHouse(house)
    }

}