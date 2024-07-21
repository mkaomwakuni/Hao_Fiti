package iz.housing.haofiti.ui.theme.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.R
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCard

@Composable
fun RentalListingScreen(navController: NavController) {
    var selectedPropertyType by remember { mutableStateOf("Villa") }

    Scaffold(
        bottomBar = { BottomNavComponent(navController = navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchBar()
            PropertyTypeSelector(
                selectedType = selectedPropertyType,
                onTypeSelected = { selectedPropertyType = it }
            )
            PropertyList()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(modifier = Modifier.padding(top = 40.dp),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.DarkGray)
                Text("Naperville, Illinois", color = Color.Black)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Change location")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle notification click */ }) {
                BadgedBox(badge = { Badge { Text("2") } }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Adjust the height as needed
    ) {
        Image(
            painter = painterResource(id = R.drawable.lavington), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, Color.White),
                        startX = Float.POSITIVE_INFINITY,
                        endX = 600f
                    )

                )
           )
        TopAppBar()
    }
}

@Composable
fun PropertyTypeSelector(selectedType: String, onTypeSelected: (String) -> Unit) {
    val types = listOf("Villa", "Hotel", "Apart", "House")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        types.forEach { type ->
            FilterChip(
                selected = type == selectedType,
                onClick = { onTypeSelected(type) },
                label = { Text(type) }
            )
        }
    }
}

@Composable
fun PropertyList() {
    val properties = listOf(
        Property("Sinbad Green Harmony Bubulak", "Bogor, West Java", 4.7f, 5.1f, "Nov 4-6"),
        Property("Jati Indah Luxury Great Palangka", "Center Borneo", 4.9f, 7.5f, "Nov 6-9")
    )

    LazyColumn {
        items(properties) { property ->
            PropertyCard(property)
        }
    }
}


data class Property(
    val name: String,
    val location: String,
    val rating: Float,
    val distance: Float,
    val dates: String
)
@Preview
@Composable
fun PreviewRel(){
    val navController = rememberNavController()
    RentalListingScreen(navController =  navController)
}