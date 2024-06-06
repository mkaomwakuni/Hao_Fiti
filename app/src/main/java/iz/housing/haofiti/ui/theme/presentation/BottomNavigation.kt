package iz.housing.haofiti.ui.theme.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.R


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Home") },
            label = { Text(stringResource(R.string.dashboard)) },
            selected = currentRoute == "article_screen",
            onClick = {
                navController.navigate("home_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(modifier = Modifier.size(25.dp), painter = painterResource(id = R.drawable.discovery), contentDescription = "Home") },
            label = { Text(stringResource(R.string.Discovery)) },
            selected = currentRoute == "Discovery_screen",
            onClick = {
                navController.navigate("Discover_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Bookmarks") },
            label = { Text(stringResource(R.string.bookmarks)) },
            selected = currentRoute == "bookmarks_screen",
            onClick = {
                navController.navigate("bookmarks_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(R.drawable.profile), contentDescription = "Profile")},
            label = { Text(stringResource(R.string.Profile)) },
            selected = currentRoute == "Profile_screen",
            onClick = {
                navController.navigate("settings_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewBottomItems() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}
