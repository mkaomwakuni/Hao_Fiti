package iz.housing.haofiti.ui.theme.presentation.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import iz.housing.haofiti.ui.theme.presentation.common.BarEffect
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent

@Composable
fun MapFocused(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            BarEffect()

            MapExplorer(navController)
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            BottomNavComponent(navController)
        }
    }
}
