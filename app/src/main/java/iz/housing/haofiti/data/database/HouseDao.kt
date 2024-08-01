package iz.housing.haofiti.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iz.housing.haofiti.data.model.PropertyItem

/**
 * Data Access Object (DAO) for the Houses table.
 * This interface defines the database operations for PropertyItem entities.
 */
@Dao
interface HouseDao {
    /**
     * Inserts a new house into the database or replaces an existing one.
     * @param house The PropertyItem to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHome(house: PropertyItem)

    /**
     * Retrieves all houses from the database.
     * @return A list of all PropertyItem entities in the database.
     */
    @Query("SELECT * FROM Houses")
    suspend fun getHouses(): List<PropertyItem>

    /**
     * Deletes a specific house from the database.
     * @param house The PropertyItem to be deleted.
     */
    @Delete
    suspend fun deleteHouse(house: PropertyItem)

    /**
     * Retrieves all saved houses from the database.
     * A house is considered saved when its 'isSaved' field is set to 1.
     * @return A list of saved PropertyItem entities.
     */
    @Query("SELECT * FROM Houses WHERE isSaved = 1")
    suspend fun getSavedHouses(): List<PropertyItem>
}