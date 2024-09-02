package iz.housing.haofiti.data.database

import android.util.Log
import iz.housing.haofiti.data.FirebaseDataProvider
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.repository.HouseRepository
import iz.housing.haofiti.viewmodels.ResponseUtil
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


    override suspend fun getSavedHomes(): ResponseUtil<List<PropertyItem>>{
        return try {
        val savedHomes = houseDao.getSavedHouses()
        Log.d("HouseRepositoryImpl", "Saved homes Retried: $savedHomes")
        ResponseUtil.Success(savedHomes)
    } catch (e: Exception) {
        Log.e("HouseRepositoryImpl", "Error retrieving saved homes", e)
        ResponseUtil.Error(message = "Error Occurred")
        }
    }

    override suspend fun insertHomes(house: PropertyItem) {
        val isSaved = house.copy(isSaved = true)
        houseDao.insertHome(isSaved)
    }

    override suspend fun deleteHome(house: PropertyItem) {
        val unSaved = house.copy(isSaved = false)
        houseDao.deleteHouse(unSaved)
    }

}