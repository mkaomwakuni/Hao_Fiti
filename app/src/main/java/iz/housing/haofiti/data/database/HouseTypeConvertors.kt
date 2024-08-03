package iz.housing.haofiti.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import iz.housing.haofiti.data.model.Agent
import iz.housing.haofiti.data.model.Amenities

/**
 * Type converters for the House database.
 * This class provides methods to convert complex types to and from types that Room can persist.
 */
@ProvidedTypeConverter
class HouseTypeConvertors {
    private val gson = Gson()

    /**
     * Converts a JSON string to a List of Strings.
     * @param value The JSON string representation of the list.
     * @return The List of Strings.
     */
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    /**
     * Converts a List of Strings to a JSON string.
     * @param list The List of Strings to convert.
     * @return The JSON string representation of the list.
     */
    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    /**
     * Converts a JSON string to an Amenities object.
     * @param value The JSON string representation of the Amenities.
     * @return The Amenities object.
     */
    @TypeConverter
    fun fromAmenities(value: String): Amenities {
        return gson.fromJson(value, Amenities::class.java)
    }

    /**
     * Converts an Amenities object to a JSON string.
     * @param amenities The Amenities object to convert.
     * @return The JSON string representation of the Amenities.
     */
    @TypeConverter
    fun fromAmenities(amenities: Amenities): String {
        return gson.toJson(amenities)
    }

    /**
     * Converts an Agent object to a JSON string.
     * @param agent The Agent object to convert.
     * @return The JSON string representation of the Agent.
     */
    @TypeConverter
    fun fromAgent(agent: Agent): String {
        return gson.toJson(agent)
    }

    /**
     * Converts a JSON string to an Agent object.
     * @param agentString The JSON string representation of the Agent.
     * @return The Agent object.
     */
    @TypeConverter
    fun toAgent(agentString: String): Agent {
        return gson.fromJson(agentString, Agent::class.java)
    }
}