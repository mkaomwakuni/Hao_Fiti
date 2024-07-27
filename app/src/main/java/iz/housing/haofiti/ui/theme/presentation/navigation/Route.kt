package iz.housing.haofiti.ui.theme.presentation.navigation

import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.DETAILS_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.EXPLORE_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.HOME_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.MAPS_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.PROFILE_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.SAVED_SCREEN

sealed class Route (val route: String) {
    data object Bookmarks : Route(SAVED_SCREEN)
    data object Home : Route(HOME_SCREEN)
    data object Maps : Route(MAPS_SCREEN)
    data object Profile : Route(PROFILE_SCREEN)
    data object Explore : Route(EXPLORE_SCREEN)
    object HouseDetails : Route("${ScreensConstants.DETAILS_SCREEN}/{propertyId}") {
        fun createRoute(propertyId: Int) = "${ScreensConstants.DETAILS_SCREEN}/$propertyId"
    }
}