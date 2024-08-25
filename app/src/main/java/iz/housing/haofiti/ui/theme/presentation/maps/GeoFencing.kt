package iz.housing.haofiti.ui.theme.presentation.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.common.createChipBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


/**
 * A composable function that displays a Google Map with property markers and geofencing.
 *
 * @param properties List of PropertyItem objects to display on the map
 * @param locationQuery String representing the location to focus on
 * @param onMarkerClick Callback function triggered when a marker is clicked
 */
@SuppressLint("MissingPermission")
@Composable
fun GoogleMapViewGeoFence(
    navController: NavController,
    properties: List<PropertyItem>,
    locationQuery: String,
    onMarkerClick: (PropertyItem) -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    val context = LocalContext.current

    // Initialize Places API client
    val placesClient = Places.createClient(context)

    // Pre-generate bitmaps for each property to improve performance
    val propertyBitmaps = remember(properties) {
        properties.associateWith { property ->
            val priceInK = (property.price / 1000).toInt()
            createChipBitmap(context, priceInK)
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize()
    ) { mapView ->
        mapView.getMapAsync { googleMap ->
            googleMap.clear()
            googleMap.uiSettings.isZoomControlsEnabled = true

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val place = getPlaceDetails(placesClient, locationQuery)
                    place?.let {
                        // Set camera position based on the found place
                        setCameraPosition(googleMap, place)

                        // Filter properties within the viewport or near the place
                        val propertiesInArea = filterPropertiesInArea(properties, place.viewport ?: place.latLng)

                        // Add markers for filtered properties
                        addPropertyMarkers(googleMap, propertiesInArea, propertyBitmaps, onMarkerClick)
                    } ?: showDefaultView(googleMap)
                } catch (e: Exception) {
                    showDefaultView(googleMap)
                }
            }
        }
    }
}

/**
 * Fetches place details from the Places API based on the given query.
 *
 * @param placesClient PlacesClient instance to make API calls
 * @param query String representing the location to search for
 * @return Place object containing details of the found place, or null if not found
 */
private suspend fun getPlaceDetails(placesClient: PlacesClient, query: String): Place? {
    val request = FindAutocompletePredictionsRequest.builder()
        .setQuery(query)
        .build()

    val response = placesClient.findAutocompletePredictions(request).await()
    val placeId = response.autocompletePredictions.firstOrNull()?.placeId

    return placeId?.let {
        val placeFields = listOf(Place.Field.LAT_LNG, Place.Field.VIEWPORT)
        val fetchPlaceRequest = FetchPlaceRequest.builder(it, placeFields).build()
        placesClient.fetchPlace(fetchPlaceRequest).await().place
    }
}

/**
 * Filters properties based on their location within a given area.
 *
 * @param properties List of PropertyItem objects to filter
 * @param area LatLngBounds or LatLng representing the area to filter within
 * @return Filtered list of PropertyItem objects
 */
private fun filterPropertiesInArea(properties: List<PropertyItem>, area: Any): List<PropertyItem> {
    return when (area) {
        is LatLngBounds -> properties.filter { property ->
            area.contains(LatLng(property.latitude, property.longitude))
        }
        is LatLng -> properties.filter { property ->
            val results = FloatArray(1)
            android.location.Location.distanceBetween(
                area.latitude, area.longitude,
                property.latitude, property.longitude,
                results
            )
            results[0] <= 100000 // Within 100km of the center
        }
        else -> properties
    }
}

/**
 * Sets the camera position on the map based on the given place.
 *
 * @param googleMap GoogleMap instance to set the camera on
 * @param place Place object containing location information
 */
private fun setCameraPosition(googleMap: GoogleMap, place: Place) {
    place.viewport?.let { viewport ->
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(viewport, 0))
    } ?: place.latLng?.let { latLng ->
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }
}

/**
 * Adds markers for the given properties on the map.
 *
 * @param googleMap GoogleMap instance to add markers to
 * @param properties List of PropertyItem objects to add as markers
 * @param propertyBitmaps Map of PropertyItem to Bitmap for marker icons
 * @param onMarkerClick Callback function triggered when a marker is clicked
 */
private fun addPropertyMarkers(
    googleMap: GoogleMap,
    properties: List<PropertyItem>,
    propertyBitmaps: Map<PropertyItem, android.graphics.Bitmap>,
    onMarkerClick: (PropertyItem) -> Unit
) {
    properties.forEach { property ->
        val position = LatLng(property.latitude, property.longitude)
        val markerOptions = MarkerOptions()
            .position(position)
            .icon(BitmapDescriptorFactory.fromBitmap(propertyBitmaps[property]!!))
            .anchor(0.5f, 1.0f)

        val marker = googleMap.addMarker(markerOptions)
        marker?.tag = property
    }

    googleMap.setOnMarkerClickListener { marker ->
        (marker.tag as? PropertyItem)?.let { property ->
            onMarkerClick(property)
        }
        true
    }
}

/**
 * Shows a default view on the map when no specific location is found.
 *
 * @param googleMap GoogleMap instance to show the default view on
 */
private fun showDefaultView(googleMap: GoogleMap) {
    val defaultLocation = LatLng(-1.6741, 41.8994)
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 2f))
}