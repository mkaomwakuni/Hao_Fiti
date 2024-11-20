package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCardHorizontal
import iz.housing.haofiti.ui.theme.presentation.navigation.Route

/**
 * A composable function that displays a map explorer screen with property markers and details.
 *
 * @param navController Navigation controller for handling navigation events.
 * @param properties List of PropertyItems to display on the map.
 */
@Composable
fun MapExplorer(
    navController: NavController,
    properties: List<PropertyItem>,
    onEvent: (HouseEvent) -> Unit
) {
    var selectedProperty by remember { mutableStateOf<PropertyItem?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Pass selected property to GoogleMapView to handle marker states
        GoogleMapView(
            properties = properties,
            selectedProperty = selectedProperty,
            onMarkerClick = { property ->
                // Toggle selection: if clicking same property, deselect it
                selectedProperty = if (selectedProperty?.id == property.id) {
                    null
                } else {
                    property
                }
            }
        )

        // Display property card for selected property
        selectedProperty?.let { property ->
            Box(
                modifier = Modifier
                    .padding(top = 550.dp, start = 20.dp, end = 20.dp)
            ) {
                PropertyCardHorizontal(
                    property = property,
                    onItemClick = {
                        onEvent(HouseEvent.OnCardClicked(property))
                        navController.navigate(Route.HouseDetails.createRoute(property.id))
                    }
                )
            }
        }
    }
}