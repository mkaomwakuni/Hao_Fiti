package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.common.BarEffect
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.viewmodels.HouseViewModel

@Composable
fun MapFocused(
    navController: NavController,
    houseViewModel: HouseViewModel = hiltViewModel(),
    onEvent: (HouseEvent) -> Unit
) {
    val uiState by houseViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            BarEffect()

            MapExplorer(
                navController = navController,
                properties = uiState.properties,
                onEvent = onEvent
            )
        }
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            BottomNavComponent(navController)
        }
    }
}