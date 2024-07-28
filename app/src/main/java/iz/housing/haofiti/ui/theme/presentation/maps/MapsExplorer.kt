package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapExplorer(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GoogleMapView()
        }
    }

@Composable
fun GoogleMapView() {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle()

    AndroidView({ mapView }) { mapView ->
        mapView.getMapAsync { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            // Set up initial markers
            val kenya = LatLng(0.0236, 37.9062)
            googleMap.addMarker(MarkerOptions().position(kenya).title("Marker in Kenya"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kenya, 10f))
            // Add markers
            val markerOptions = MarkerOptions()
                .position(kenya)
                .title("Nairobi")
            googleMap.addMarker(markerOptions)
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            onCreate(null)
        }
    }

    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycle.removeObserver(lifecycleObserver) }
    }

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): DefaultLifecycleObserver {
    return remember(mapView) {
        object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                mapView.onResume()
            }

            override fun onStart(owner: LifecycleOwner) {
                mapView.onStart()
            }

            override fun onStop(owner: LifecycleOwner) {
                mapView.onStop()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.onPause()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                mapView.onDestroy()
            }

            override fun onCreate(owner: LifecycleOwner) {
                mapView.onCreate(null)
            }
        }
    }
}
