package iz.housing.haofiti.data.repository

import iz.housing.haofiti.data.model.PropertyItem
import kotlinx.coroutines.flow.Flow


interface HouseRepository {
    fun getAllProperties(): Flow<List<PropertyItem>>
    fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>>
    fun getAvailableLocations(): Flow<List<String>>
}