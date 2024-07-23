package iz.housing.haofiti.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import iz.housing.haofiti.data.model.PropertyItem

@Database(entities = [PropertyItem::class],version = 1,exportSchema = false)
abstract class HouseDatabase:RoomDatabase (){
    abstract fun houseDao(): HouseDao
}