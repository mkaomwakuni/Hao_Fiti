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
    entities = [PropertyItem::class], // Defines the entities (tables) in the database
    version = 1, // The version of the database schema
    exportSchema = false // Disables schema version control for simplicity
)
@TypeConverters(HouseTypeConvertors::class) // Specifies custom type converters for complex data types
abstract class HouseDatabase : RoomDatabase() {
    /**
     * Provides access to the HouseDao for database operations.
     * @return An instance of HouseDao.
     */
    abstract fun houseDao(): HouseDao
}