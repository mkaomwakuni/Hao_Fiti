package iz.housing.haofiti.ui.theme.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCard

@Composable
fun RentalListingScreen(navHostController: NavController) {
    var selectedPropertyType by remember { mutableStateOf("Villa") }

    Scaffold(
        topBar = { TopAppBar() }
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
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Text("Naperville, Illinois")
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Change location")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle notification click */ }) {
                BadgedBox(badge = { Badge { Text("2") } }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                }
            }
        }
    )
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = { /* TODO: Handle search */ },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .padding(16.dp),
        placeholder = { Text("Search apart, hotel, etc...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.Menu, contentDescription = "Filters") }
    )
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
    RentalListingScreen(navHostController = navController)
}