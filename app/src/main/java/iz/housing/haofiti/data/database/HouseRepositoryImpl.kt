package iz.housing.haofiti.data.database

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import iz.housing.haofiti.data.FirebaseDataProvider
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.repository.HouseRepository
import iz.housing.haofiti.viewmodels.ResponseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HouseRepositoryImpl  @Inject constructor(
    private  val houseDao: HouseDao,
    private val firebaseDataProvider: FirebaseDataProvider
) : HouseRepository {

    override suspend fun getAllProperties(): Flow<List<PropertyItem>> = firebaseDataProvider.getAllProperties()

    override suspend fun getPropertiesByLocation(location: String): Flow<List<PropertyItem>> = firebaseDataProvider.getPropertyByLocation(location)

    override suspend fun getAvailableLocations(): Flow<List<String>> = firebaseDataProvider.getAllLocations()

    override suspend fun searchQuery(query: String): Flow<List<PropertyItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedHomes(): ResponseUtil<List<PropertyItem>>{
        return try {
        val savedHomes = houseDao.getSavedHouses()
        Log.d("HouseRepositoryImpl", "Saved homes Retried: $savedHomes")
        ResponseUtil.Success(savedHomes)
    } catch (e: Exception) {
        Log.e("HouseRepositoryImpl", "Error retrieving saved homes", e)
        ResponseUtil.Error(message = "Error Occurred")
        }
    }

    override suspend fun insertHomes(house: PropertyItem) {
        val isSaved = house.copy(isSaved = true)
        houseDao.insertHome(isSaved)
    }

    override suspend fun deleteHome(house: PropertyItem) {
        val unSaved = house.copy(isSaved = false)
        houseDao.deleteHouse(unSaved)
    }

    override suspend fun getCurrentLocation(context: Context): ResponseUtil<String> {
        return withContext(Dispatchers.IO) {
            try {
                if (!checkLocationPermission(context)) {
                    return@withContext ResponseUtil.Error("Location permission not granted")
                }

                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                val locationRequest = com.google.android.gms.location.LocationRequest.create().apply {
                    interval = 10000 // 10 seconds
                    fastestInterval = 5000 // 5 seconds
                    priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
                }

                val locationResult = fusedLocationClient.getCurrentLocation(
                    com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY, null
                ).await()

                if (locationResult != null) {
                    val geocoder = Geocoder(context)
                    val addresses = geocoder.getFromLocation(locationResult.latitude, locationResult.longitude, 1)

                    val cityName = addresses?.firstOrNull()?.locality ?: "Urban"
                    ResponseUtil.Success(cityName)
                } else {
                    ResponseUtil.Error("Could not retrieve a valid location")
                }
            } catch (e: Exception) {
                Log.e("HouseRepositoryImpl", "Error getting location", e)
                ResponseUtil.Error(e.message ?: "Failed to get location")
            }
        }
    }




    override suspend fun checkLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}

}