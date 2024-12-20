package iz.housing.haofiti.ui.theme.presentation.navigation

import AuthScreen
import android.app.Activity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import iz.housing.haofiti.ui.theme.presentation.Chat.ContactForm
import iz.housing.haofiti.ui.theme.presentation.booked.BookmarkScreen
import iz.housing.haofiti.ui.theme.presentation.common.SearchScreen
import iz.housing.haofiti.ui.theme.presentation.details.HouseDetailsPage
import iz.housing.haofiti.ui.theme.presentation.home.HomePage
import iz.housing.haofiti.ui.theme.presentation.maps.MapFocused
import iz.housing.haofiti.ui.theme.presentation.profile.SettingsScreen
import iz.housing.haofiti.viewmodels.AuthViewModel
import iz.housing.haofiti.viewmodels.HouseViewModel

@Composable
fun HouseNavGraph(navController: NavHostController) {
    val houseViewModel: HouseViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Route.Auth.route) {
        composable(
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            route = Route.Auth.route) {
            AuthScreen(
                navController = navController,
                viewModel = authViewModel,
                houseViewModel = houseViewModel
            )
        }
        composable(
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            route = Route.Home.route) {
            HomePage(
                navController = navController,
                state = houseViewModel.uiState.collectAsState().value,
                onEvent = houseViewModel::onEvent,
                onItemClick = { propertyId ->
                    houseViewModel.getPropertyById(propertyId.name)
                    navController.navigate(Route.HouseDetails.route)
                }
            )
        }
        composable(
            route = Route.Search.route,
            enterTransition = { fadeIn(animationSpec = tween(3000)) },
            exitTransition = { slideOutHorizontally (animationSpec = tween(30)) }
        ) {
            SearchScreen(navController = navController)
        }
        composable(route = Route.Maps.route) {
            MapFocused(
                navController = navController,
                onEvent = houseViewModel::onEvent
            )
        }
        composable(route = Route.Bookmarks.route) {
            BookmarkScreen(
                navController = navController,
                houseViewModel = houseViewModel)
        }
        composable(route = Route.Profile.route) {
            SettingsScreen(navController)
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
        composable(ScreensConstants.CHAT_SCREEN) {
            ContactForm(navController)
        }
    }
}
