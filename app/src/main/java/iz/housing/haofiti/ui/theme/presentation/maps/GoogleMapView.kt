package iz.housing.haofiti.ui.theme.presentation.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.common.createChipBitmap

/**
 * A composable function that displays a Google Map with markers for a list of properties.
 *
 * @param properties A list of PropertyItem objects to be displayed on the map.
 * @param onMarkerClick A lambda function to be called when a marker is clicked, with the clicked PropertyItem as its parameter.
 */

@SuppressLint("PotentialBehaviorOverride")
@Composable
fun GoogleMapView(properties: List<PropertyItem>, onMarkerClick: (PropertyItem) -> Unit) {
    val mapView = rememberMapViewWithLifecycle()
    val context = LocalContext.current

    // Pre-generate bitmaps for each property
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

            if (properties.isNotEmpty()) {
                val boundsBuilder = LatLngBounds.Builder()
                var hasValidCoordinates = false

                properties.forEach { property ->
                    if (property.latitude != 0.0 && property.longitude != 0.0) {
                        val position = LatLng(property.latitude, property.longitude)
                        val markerOptions = MarkerOptions()
                            .position(position)
                            .icon(BitmapDescriptorFactory.fromBitmap(propertyBitmaps[property]!!))
                            .anchor(0.5f, 1.0f)

                        val marker = googleMap.addMarker(markerOptions)
                        marker?.tag = property
                        boundsBuilder.include(position)
                        hasValidCoordinates = true
                    }
                }

                if (hasValidCoordinates) {
                    val bounds = boundsBuilder.build()
                    val padding = 100 // pixels
                    val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    googleMap.animateCamera(cameraUpdate)
                } else {
                    showDefaultView(googleMap)
                }

                googleMap.setOnMarkerClickListener { marker ->
                    (marker.tag as? PropertyItem)?.let { property ->
                        onMarkerClick(property)
                    }
                    true
                }
            } else {
                showDefaultView(googleMap)
            }
        }
    }
}

private fun showDefaultView(googleMap: GoogleMap) {
    val defaultLocation = (LatLng(-1.6741, 41.8994) )
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 2f))
}