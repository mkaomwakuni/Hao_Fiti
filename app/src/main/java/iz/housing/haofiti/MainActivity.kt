package iz.housing.haofiti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import iz.housing.haofiti.ui.theme.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.navigation.HouseNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HaoFitiTheme {
               val navController = rememberNavController()
                HouseNavGraph(navController = navController)
                }
            }
        }
    }

