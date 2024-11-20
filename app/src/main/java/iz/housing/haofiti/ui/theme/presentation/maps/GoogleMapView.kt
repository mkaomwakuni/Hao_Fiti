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
import iz.housing.haofiti.ui.theme.presentation.common.DrawableEuroChip
import iz.housing.haofiti.ui.theme.presentation.common.createChipBitmap

/**
 * A composable function that displays a Google Map with markers for a list of properties.
 *
 * @param properties A list of PropertyItem objects to be displayed on the map.
 * @param onMarkerClick A lambda function to be called when a marker is clicked, with the clicked PropertyItem as its parameter.
 */


@SuppressLint("PotentialBehaviorOverride")
@Composable
fun GoogleMapView(
    properties: List<PropertyItem>,
    selectedProperty: PropertyItem?,
    onMarkerClick: (PropertyItem) -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    val context = LocalContext.current

    // Keep track of marker states and their associated drawables
    val markerStates = remember(properties, selectedProperty) {
        properties.associate { property ->
            val priceInK = (property.price / 1000).toInt()
            val drawable = DrawableEuroChip(context, priceInK).apply {
                // Set initial state based on selection
                if (property.id == selectedProperty?.id) {
                    toggleClickState()
                }
            }
            property to drawable
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
                        val drawable = markerStates[property]
                        val bitmap = createChipBitmap(context, (property.price / 1000).toInt())

                        val markerOptions = MarkerOptions()
                            .position(position)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            .anchor(0.5f, 1.0f)

                        val marker = googleMap.addMarker(markerOptions)
                        marker?.tag = property
                        boundsBuilder.include(position)
                        hasValidCoordinates = true
                    }
                }

                if (hasValidCoordinates) {
                    val bounds = boundsBuilder.build()
                    val padding = 100
                    val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    googleMap.animateCamera(cameraUpdate)
                } else {
                    showDefaultView(googleMap)
                }

                googleMap.setOnMarkerClickListener { marker ->
                    (marker.tag as? PropertyItem)?.let { property ->
                        // Update marker appearance
                        markerStates[property]?.toggleClickState()
                        // Create new bitmap with updated state
                        val updatedBitmap = createChipBitmap(context, (property.price / 1000).toInt())
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(updatedBitmap))

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
    val defaultLocation = LatLng(-1.6741, 41.8994)
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 2f))
}