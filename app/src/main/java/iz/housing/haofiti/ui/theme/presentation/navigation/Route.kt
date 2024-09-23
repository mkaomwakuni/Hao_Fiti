package iz.housing.haofiti.ui.theme.presentation.navigation

import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.AUTH_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.EXPLORE_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.HOME_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.MAPS_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.PROFILE_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.SAVED_SCREEN
import iz.housing.haofiti.ui.theme.presentation.navigation.ScreensConstants.SEARCH_SCREEN

sealed class Route (val route: String) {
    data object Auth: Route(AUTH_SCREEN)
    data object Bookmarks : Route(SAVED_SCREEN)
    data object Home : Route(HOME_SCREEN)
    data object Maps : Route(MAPS_SCREEN)
    data object Profile : Route(PROFILE_SCREEN)
    data object Explore : Route(EXPLORE_SCREEN)
    data object Search : Route(SEARCH_SCREEN)
    data object HouseDetails : Route("${ScreensConstants.DETAILS_SCREEN}/{propertyId}") {
        fun createRoute(propertyId: Int) = "${ScreensConstants.DETAILS_SCREEN}/$propertyId"
    }
    data object Message : Route(ScreensConstants.CHAT_SCREEN)
}