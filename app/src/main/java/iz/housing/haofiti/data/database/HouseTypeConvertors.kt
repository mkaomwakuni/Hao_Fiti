package iz.housing.haofiti.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import iz.housing.haofiti.data.model.Agent
import iz.housing.haofiti.data.model.Amenities

@ProvidedTypeConverter
class HouseTypeConvertors {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromAmenities(value: String): Amenities {
        return Gson().fromJson(value, Amenities::class.java)
    }

    @TypeConverter
    fun fromAmenities(amenities: Amenities): String {
        return Gson().toJson(amenities)
    }

    @TypeConverter
    fun fromAgent(agent: Agent): String {
        return Gson().toJson(agent)
    }

    @TypeConverter
    fun toAgent(agentString: String): Agent {
        return Gson().fromJson(agentString, Agent::class.java)
    }
}