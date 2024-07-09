package iz.housing.haofiti.ui.theme.presentation.navigation

sealed class Route (val route: String,val title:String) {
    data object Bookmarks : Route("saved_screen","Saved")
    data object Home : Route("home_screen","Home")
    data object Maps : Route("maps_screen","Maps")
    data object Profile : Route("profile_screen","Profile")
    data object Explore : Route("explore_screen","Explore")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}