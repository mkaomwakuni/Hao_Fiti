package iz.housing.haofiti

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import dagger.hilt.android.AndroidEntryPoint
import iz.housing.haofiti.ui.theme.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.navigation.HouseNavGraph
import iz.housing.haofiti.viewmodels.AuthViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var authViewModel: AuthViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Facebook SDK initialization
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        authViewModel.setupFacebookLogin(this)

        // window settings
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowsInsets = WindowCompat.getInsetsController(window, window.decorView)
        windowsInsets.isAppearanceLightStatusBars = false

        // Set the content
        setContent {
            HaoFitiTheme {
                val navController = rememberNavController()
                HouseNavGraph(navController = navController)
            }
        }
    }

    // Handle Facebook login result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        authViewModel.getFacebookCallbackManager().onActivityResult(requestCode, resultCode, data)
    }
}
