package iz.housing.haofiti.ui.theme.presentation.booked

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent

@Composable
fun Bookmarks(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavComponent(navController = navController) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {

        }
    }
}