package iz.housing.haofiti

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import iz.housing.haofiti.ui.theme.presentation.common.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.navigation.HouseNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowsInsets = WindowCompat.getInsetsController(window, window.decorView)
        windowsInsets.isAppearanceLightStatusBars = false
            setContent {
                HaoFitiTheme {
                    val navController = rememberNavController()
                    HouseNavGraph(navController = navController)
                }
            }
        }
    }

