package iz.housing.haofiti.ui.theme.presentation.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import iz.housing.haofiti.data.model.PropertyItem

/**
 * A composable function that displays a Google Map with markers for a list of properties.
 *
 * @param properties A list of PropertyItem objects to be displayed on the map.
 * @param onMarkerClick A lambda function to be called when a marker is clicked, with the clicked PropertyItem as its parameter.
 */
@SuppressLint("PotentialBehaviorOverride")
@Composable
fun GoogleMapView(properties: List<PropertyItem>, onMarkerClick: (PropertyItem) -> Unit) {
    // Create a MapView that follows the lifecycle of the composable
    val mapView = rememberMapViewWithLifecycle()

    // Use AndroidView to integrate the MapView into the Compose UI
    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize()
    ) { mapView ->
        // Get the GoogleMap instance asynchronously
        mapView.getMapAsync { googleMap ->
            // Clear any existing markers on the map
            googleMap.clear()
            // Enable zoom controls on the map
            googleMap.uiSettings.isZoomControlsEnabled = true

            if (properties.isNotEmpty()) {
                val boundsBuilder = LatLngBounds.Builder()
                var hasValidCoordinates = false

                // Add markers for each property with valid coordinates
                properties.forEach { property ->
                    if (property.latitude != 0.0 && property.longitude != 0.0) {
                        val position = LatLng(property.latitude, property.longitude)
                        val markerOptions = MarkerOptions()
                            .position(position)
                            .title(property.name)
                            .snippet("${property.price} - ${property.type}")

                        val marker = googleMap.addMarker(markerOptions)
                        marker?.tag = property
                        boundsBuilder.include(position)
                        hasValidCoordinates = true
                    }
                }

                if (hasValidCoordinates) {
                    // If there are valid coordinates, adjust the camera to show all markers
                    val bounds = boundsBuilder.build()
                    val padding = 100 // pixels
                    val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    googleMap.animateCamera(cameraUpdate)
                } else {
                    // If no valid coordinates, show a default view
                    showDefaultView(googleMap)
                }

                // Set up the marker click listener
                googleMap.setOnMarkerClickListener { marker ->
                    (marker.tag as? PropertyItem)?.let { property ->
                        onMarkerClick(property)
                    }
                    true
                }
            } else {
                // If no properties, show a default view
                showDefaultView(googleMap)
            }
        }
    }
}

/**
 * Shows a default view on the map, centered on Nairobi, Kenya.
 *
 * @param googleMap The GoogleMap instance to update.
 */
private fun showDefaultView(googleMap: GoogleMap) {
    val defaultLocation = LatLng(-1.2921, 36.8219) // Nairobi, Kenya
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))
}