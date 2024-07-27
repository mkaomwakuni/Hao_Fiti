package iz.housing.haofiti.ui.theme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.ui.theme.presentation.booked.Bookmarks
import iz.housing.haofiti.ui.theme.presentation.details.HouseDetailsPage
import iz.housing.haofiti.ui.theme.presentation.explorer.Discovery
import iz.housing.haofiti.ui.theme.presentation.home.HomePage
import iz.housing.haofiti.ui.theme.presentation.home.RentalListingScreen
import iz.housing.haofiti.ui.theme.presentation.maps.MapExplorer
import iz.housing.haofiti.viewmodels.HouseViewModel

@Composable
fun HouseNavGraph(navController: NavHostController) {
    val houseViewModel: HouseViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(route = Route.Home.route) {
            HomePage(
                navController = navController,
                state = houseViewModel.uiState.collectAsState().value,
                onEvent = houseViewModel::onEvent,
                onItemClick = { propertyId ->
                    houseViewModel.getPropertyById(propertyId.id)
                    navController.navigate(Route.HouseDetails.route)
                }
            )
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
        composable(
            route = Route.HouseDetails.route,
            arguments = listOf(navArgument("propertyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getInt("propertyId") ?: return@composable
            HouseDetailsPage (
                viewModel = houseViewModel,
                propertyId = propertyId,
                navController = navController
            )
        }
    }
}