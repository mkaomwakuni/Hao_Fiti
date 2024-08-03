package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.maps.MapView

/**
 * A composable function that creates and remembers a MapView instance with proper lifecycle handling.
 *
 * @return A MapView instance that follows the composable lifecycle.
 */
@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    // Create and remember a MapView instance
    val mapView = remember {
        MapView(context).apply {
            id = android.R.id.content
        }
    }

    // Create a lifecycle observer for the MapView
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    // Add and remove the lifecycle observer
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

/**
 * A composable function that creates and remembers a lifecycle observer for a MapView.
 *
 * @param mapView The MapView instance to observe.
 * @return A DefaultLifecycleObserver that manages the MapView lifecycle.
 */
@Composable
fun rememberMapLifecycleObserver(mapView: MapView): DefaultLifecycleObserver {
    return remember(mapView) {
        object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                mapView.onCreate(null)
            }

            override fun onStart(owner: LifecycleOwner) {
                mapView.onStart()
            }

            override fun onResume(owner: LifecycleOwner) {
                mapView.onResume()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.onPause()
            }

            override fun onStop(owner: LifecycleOwner) {
                mapView.onStop()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                mapView.onDestroy()
            }
        }
    }
}