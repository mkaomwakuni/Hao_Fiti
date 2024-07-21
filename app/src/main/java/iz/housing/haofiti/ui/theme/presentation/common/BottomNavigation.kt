package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
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
fun BottomNavComponent(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home") },
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
            icon = { Icon(modifier = Modifier.size(20.dp), painter = painterResource(id = R.drawable.navigationicon), contentDescription = "Home") },
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
            icon = { Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Bookmarks") },
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
            icon = { Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Explore") },
            label = { Text(stringResource(R.string.maps)) },
            selected = currentRoute == "maps_screen",
            onClick = {
                navController.navigate("maps_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(imageVector =  Icons.Outlined.Person, contentDescription = "Profile")},
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
    BottomNavComponent(navController = navController)
}
