package iz.housing.haofiti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.ui.theme.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.navigation.NavGraph

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HaoFitiTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
                }
            }
        }
    }

