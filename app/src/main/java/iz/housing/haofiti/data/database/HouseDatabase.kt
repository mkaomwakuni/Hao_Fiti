package iz.housing.haofiti.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import iz.housing.haofiti.data.model.PropertyItem

/**
 * Room database class for the Housing application.
 * This class defines the database configuration and serves as the main access point for the underlying connection.
 */
@Database(
    entities = [PropertyItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(HouseTypeConvertors::class)
abstract class HouseDatabase : RoomDatabase() {
    /**
     * Provides access to the HouseDao for database operations.
     * @return An instance of HouseDao.
     */
    abstract fun houseDao(): HouseDao
}