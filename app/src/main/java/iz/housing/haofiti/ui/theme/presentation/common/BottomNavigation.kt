package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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

    NavigationBar(modifier = Modifier
        .height(80.dp)
        .clip(RoundedCornerShape(20.dp))
        .fillMaxWidth()) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home") },
            label = {  },
            selected = currentRoute == "home_screen",
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
            label = {  },
            selected = currentRoute == "explore_screen",
            onClick = {
                navController.navigate("explore_screen") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Bookmarks") },
            label = {  },
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
            label = {  },
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
            label = {  },
            selected = currentRoute == "profile_screen",
            onClick = {
                navController.navigate("profile_screen") {
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
