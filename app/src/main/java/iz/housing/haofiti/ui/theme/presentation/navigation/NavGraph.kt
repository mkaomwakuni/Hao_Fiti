package iz.housing.haofiti.ui.theme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import iz.housing.haofiti.data.database.HouseInfo
import iz.housing.haofiti.ui.theme.presentation.details.DetailScreen
import iz.housing.haofiti.ui.theme.presentation.discover.ExploreUi
import iz.housing.haofiti.ui.theme.presentation.home.Homepage
import iz.housing.haofiti.ui.theme.presentation.splash.SplashScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavScreens.SplashScreen.route){

        composable(route = NavScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(route = NavScreens.Homepage.route){
            Homepage(navController)
        }
        composable(route = NavScreens.Details.route){

            val result = navController.previousBackStackEntry?.savedStateHandle?.get<HouseInfo>("HouseInfo")
            if (result != null) {
                DetailScreen(navController,result)
            }
        }
        composable( route = NavScreens.Explore.route + "/{categoryTitle}",
            arguments = listOf(
                navArgument("categoryTitle") {
                    type = NavType.StringType
                }
            )
        ){
            it.arguments?.getString("categoryTitle")
                ?.let { it1 -> ExploreUi(navController) }
        }
    }
}