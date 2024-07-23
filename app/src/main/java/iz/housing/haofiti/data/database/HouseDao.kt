package iz.housing.haofiti.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.explorer.House

@Dao
interface HouseDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHome(house: PropertyItem)

    @Query("SELECT * FROM Houses")
    suspend fun getHouses(): List<House>


    @Delete
    suspend fun deleteHouse(house: PropertyItem)

    @Query("SELECT  * FROM Houses WHERE isSaved = 1")
    suspend fun getSavedHouses(): List<PropertyItem>

}