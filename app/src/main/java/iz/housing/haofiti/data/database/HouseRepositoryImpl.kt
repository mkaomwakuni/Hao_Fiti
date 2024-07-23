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

    override fun getAllProperties(): Flow<List<PropertyItem>> = firebaseDataProvider.getAllProperties()

    override fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>> = firebaseDataProvider.getPropertyByLocation(location)

    override fun getAvailableLocations(): Flow<List<String>> = firebaseDataProvider.getAllLocations()

    suspend fun getSavedHouses(): List<PropertyItem> = houseDao.getSavedHouses()

    suspend fun insertHome(house: PropertyItem) = houseDao.insertHome(house)

    suspend fun deleteHouse(house: PropertyItem) = houseDao.deleteHouse(house)
}