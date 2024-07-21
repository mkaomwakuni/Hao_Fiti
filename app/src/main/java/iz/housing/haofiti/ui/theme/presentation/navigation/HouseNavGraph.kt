package iz.housing.haofiti.ui.theme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import iz.housing.haofiti.ui.theme.presentation.home.RentalListingScreen

@Composable
fun HouseNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(route = Route.Home.route) {
            //HouseDetailsPage(navController)
        }
        composable(route = Route.Bookmarks.route) {

        }
        composable(route = Route.Maps.route) {
            //MapExplorer(navController)
        }
        composable(route = Route.Profile.route) {
            RentalListingScreen(navController)
        }

    }
}