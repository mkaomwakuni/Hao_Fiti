package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import iz.housing.haofiti.R

@Composable
fun BottomNavComponent(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.homepage),
                    contentDescription = "Home"
                )
            },
            label = { Text(text = "Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.navigationicon),
                    contentDescription = "Explore"
                )
            },
            label = { Text(text = "Explore") },
            selected = currentRoute == "explore",
            onClick = { navController.navigate("explore") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like_outlined),
                    contentDescription = "Recommendations"
                )
            },
            label = { Text(text = "Recommendations") },
            selected = currentRoute == "maps",
            onClick = { navController.navigate("maps") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile"
                )
            },
            label = { Text(text = "Profile") },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
}
