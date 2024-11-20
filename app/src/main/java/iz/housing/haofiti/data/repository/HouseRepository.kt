package iz.housing.haofiti.data.repository

import android.content.Context
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.viewmodels.ResponseUtil
import kotlinx.coroutines.flow.Flow


/**
 * Repository interface for managing property-related data operations.
 * This interface defines the contract for accessing and manipulating property data,
 * abstracting the data source implementations.
 */
interface HouseRepository {
    /**
     * Retrieves all properties.
     *
     * @return A Flow emitting a List of PropertyItem objects representing all properties.
     */
    suspend fun getAllProperties(): Flow<List<PropertyItem>>

    /**
     * Retrieves properties filtered by a specific location.
     *
     * @param location The location to filter properties by.
     * @return A Flow emitting a List of PropertyItem objects for the specified location.
     */
    suspend fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>>

    /**
     * Retrieves a list of all available locations.
     *
     * @return A Flow emitting a List of Strings representing available locations.
     */
    suspend fun getAvailableLocations(): Flow<List<String>>

    /**
     * Searches for properties based on a query string.
     *
     * @param query The search query string.
     * @return A Flow emitting a List of PropertyItem objects matching the search query.
     */
    suspend fun searchQuery(query: String): Flow<List<PropertyItem>>

    /**
     * Retrieves a list of saved (favorite) homes.
     *
     * @return A List of PropertyItem objects representing saved homes.
     */
    suspend fun getSavedHomes(): ResponseUtil<List<PropertyItem>>

    /**
     * Inserts a new home into the saved homes list.
     *
     * @param house The PropertyItem object to be saved.
     */
    suspend fun insertHomes(house: PropertyItem)

    /**
     * Removes a home from the saved homes list.
     *
     * @param house The PropertyItem object to be removed.
     */
    suspend fun deleteHome(house: PropertyItem)

    suspend fun getCurrentLocation(context: Context): ResponseUtil<String>

    suspend fun checkLocationPermission(context: Context): Boolean
}