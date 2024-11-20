package iz.housing.haofiti

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import iz.housing.haofiti.ui.theme.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.navigation.HouseNavGraph
import iz.housing.haofiti.viewmodels.HouseViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowsInsets = WindowCompat.getInsetsController(window, window.decorView)
        windowsInsets.isAppearanceLightStatusBars = false
            setContent {
                val viewModel: HouseViewModel = hiltViewModel()
                val isDarkMode by viewModel.isDarkMode.collectAsState()
                HaoFitiTheme(darkTheme = isDarkMode) {
                    val navController = rememberNavController()
                    HouseNavGraph(navController = navController)
                }
            }
        }
    }

