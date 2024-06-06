package iz.housing.haofiti

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import iz.housing.haofiti.ui.theme.HaoFitiTheme
import iz.housing.haofiti.ui.theme.presentation.Details

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HaoFitiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Details()
                }
            }
        }
    }
}

