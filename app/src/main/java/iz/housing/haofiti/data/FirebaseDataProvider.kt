package iz.housing.haofiti.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.PropertyType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataProvider @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val houseRef: DatabaseReference = database.getReference("housing")

    fun getAllProperties(): Flow<List<PropertyItem>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val properties = mutableListOf<PropertyItem>()
                snapshot.children.forEach { locationSnapshot ->
                    val locationName = locationSnapshot.key ?: ""
                    locationSnapshot.children.forEach { propertySnapshot ->
                        addPropertiesOfType(propertySnapshot, "apartments", PropertyType.APARTMENT, locationName, properties)
                        addPropertiesOfType(propertySnapshot, "villas", PropertyType.VILLA, locationName, properties)
                        addPropertiesOfType(propertySnapshot, "bungalows", PropertyType.BUNGALOW, locationName, properties)
                        addPropertiesOfType(propertySnapshot, "rentals", PropertyType.RENTAL, locationName, properties)
                    }
                }
                trySend(properties)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        houseRef.addValueEventListener(listener)

        awaitClose {
            houseRef.removeEventListener(listener)
        }
    }

    fun getPropertyByLocation(location: String): Flow<List<PropertyItem>> = callbackFlow {
        val houseListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val properties = mutableListOf<PropertyItem>()
                snapshot.child(location).children.forEach { propertySnapshot ->
                    addPropertiesOfType(propertySnapshot, "apartment", PropertyType.APARTMENT, location, properties)
                    addPropertiesOfType(propertySnapshot, "villas", PropertyType.VILLA, location, properties)
                    addPropertiesOfType(propertySnapshot, "bungalow", PropertyType.BUNGALOW, location, properties)
                    addPropertiesOfType(propertySnapshot, "rental", PropertyType.RENTAL, location, properties)
                }
                trySend(properties)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        houseRef.addValueEventListener(houseListener)
        awaitClose {
            houseRef.removeEventListener(houseListener)
        }
    }

    fun addPropertiesOfType(propertySnapshot: DataSnapshot, childName: String, type: PropertyType, locationName: String, properties: MutableList<PropertyItem>) {
        propertySnapshot.child(childName).children.forEach { itemSnapshot ->
            try {
                itemSnapshot.getValue(PropertyItem::class.java)?.let {
                    properties.add(it.copy(type = type, location = locationName))
                }
            } catch (e: Exception) {
                Log.e("FirebaseDataProvider", "Error deserializing property item", e)
            }
        }
    }

    fun getAllLocations(): Flow<List<String>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val locations = snapshot.children.mapNotNull { it.key }
                trySend(locations)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        houseRef.addValueEventListener(listener)

        awaitClose {
            houseRef.removeEventListener(listener)
        }
    }
}