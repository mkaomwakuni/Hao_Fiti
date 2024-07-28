package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCardHorizontal

/**
 * A composable function that displays a map explorer screen with property markers and details.
 *
 * @param navController Navigation controller for handling navigation events.
 * @param properties List of PropertyItems to display on the map.
 */
@Composable
fun MapExplorer(navController: NavController, properties: List<PropertyItem>) {
    // State to keep track of the currently selected property
    var selectedProperty by remember { mutableStateOf<PropertyItem?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Display the Google Map with property markers
        GoogleMapView(properties) { property ->
            selectedProperty = property
        }

        // Display a property card for the selected property
        selectedProperty?.let { property ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                PropertyCardHorizontal(
                    property = property,
                    onItemClick = {
                        // Handle property card click if needed
                    }
                )
            }
        }
    }
}