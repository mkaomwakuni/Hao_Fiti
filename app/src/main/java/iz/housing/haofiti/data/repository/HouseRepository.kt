package iz.housing.haofiti.data.repository

import androidx.room.Query
import iz.housing.haofiti.data.model.PropertyItem
import kotlinx.coroutines.flow.Flow


interface HouseRepository {
    suspend fun getAllProperties(): Flow<List<PropertyItem>>

    suspend fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>>

    suspend fun getAvailableLocations(): Flow<List<String>>

    suspend fun searchQuery(query: String): Flow<List<PropertyItem>>

    suspend fun getSavedHomes(): List<PropertyItem>

    suspend fun insertHomes(house: PropertyItem)

    suspend fun deleteHome(house: PropertyItem)

}