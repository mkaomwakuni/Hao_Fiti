package iz.housing.haofiti.ui.theme.presentation.navigation

import iz.housing.haofiti.ui.theme.presentation.navigation.NavScreenConstants.DETAILS
import iz.housing.haofiti.ui.theme.presentation.navigation.NavScreenConstants.EXPLORER
import iz.housing.haofiti.ui.theme.presentation.navigation.NavScreenConstants.HOMEPAGE
import iz.housing.haofiti.ui.theme.presentation.navigation.NavScreenConstants.SPLASHSCREEN

sealed class NavScreens (val route: String){
    data object SplashScreen: NavScreens(SPLASHSCREEN)
    data object Homepage:NavScreens(HOMEPAGE)
    data object Details:NavScreens(DETAILS)
    data object Explore:NavScreens(EXPLORER)

    fun withArgs(vararg args:String): String{
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}