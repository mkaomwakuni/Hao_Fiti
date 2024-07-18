package iz.housing.haofiti.ui.theme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import iz.housing.haofiti.ui.theme.presentation.booked.Bookmarks
import iz.housing.haofiti.ui.theme.presentation.explorer.Discovery
import iz.housing.haofiti.ui.theme.presentation.home.HomePage
import iz.housing.haofiti.ui.theme.presentation.home.RentalListingScreen
import iz.housing.haofiti.ui.theme.presentation.maps.MapExplorer
import iz.housing.haofiti.viewmodels.HouseViewModel


@Composable
fun HouseNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(route = Route.Home.route) {
            val viewModel: HouseViewModel = hiltViewModel()
            HomePage(viewModel,navController)
        }
        composable(route = Route.Maps.route) {
            MapExplorer(navController)
        }
        composable(route = Route.Bookmarks.route) {
            Bookmarks(navController)
        }
        composable(route = Route.Explore.route) {
            Discovery(navController)
        }
        composable(route = Route.Profile.route) {
            RentalListingScreen(navController)
        }

    }
}