package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import iz.housing.haofiti.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { /* Perform search */ },
            active = isSearchActive,
            onActiveChange = { isSearchActive = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (isSearchActive) {
                    IconButton(onClick = {
                        if (searchQuery.isNotEmpty()) {
                            searchQuery = ""
                        } else {
                            isSearchActive = false
                        }
                    }) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.clear))
                    }
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface,
                dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            ),
            placeholder = { Text(stringResource(R.string.locality)) }
        ) {
            // Search suggestions
            val suggestions = listOf("Nairobi", "Mombasa", "Kilifi", "Nakuru", "Kwale")
            LazyColumn {
                items(suggestions.size) { index ->
                    SuggestionItem(icon = Icons.Default.LocationOn, text = suggestions[index])
                }
            }
        }

        if (!isSearchActive) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        stringResource(R.string.search_options),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SearchOptionCard(
                            title = stringResource(R.string.draw_on_map),
                            icon = painterResource(id = R.drawable.bed),
                            modifier = Modifier.weight(1f),
                          //  onCardClicked = {}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SearchOptionCard(
                            title = stringResource(R.string.search_by_county),
                            icon = painterResource(id = R.drawable.bedroom),
                            modifier = Modifier.weight(1f),
                           // onCardClicked = {}
                        )
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SearchOptionCard(
                            title = "Search near you",
                            icon = painterResource(id = R.drawable.house),
                            modifier = Modifier.weight(1f),
                            showNewBadge = true
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SearchOptionCard(
                            title = "Search by city",
                            icon = painterResource(id = R.drawable.house),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                item {
                    Text(
                        stringResource(R.string.your_recent_searches),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                item {
//                    RecentSearchItem(
//                        title = "Area drawn on map",
//                        subtitle = "House - Apartment • Rent",
//                        description = "All types"
//                    )
                }
            }
        }
    }
}

@Composable
fun SuggestionItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun SearchOptionCard(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    showNewBadge: Boolean = false,
    //onCardClicked: HouseEvent.OnCardClicked
) {
    TextButton (
        onClick = { /* Handle click */ },
        modifier = modifier
            .shadow(1.dp)
            .padding(vertical = 8.dp)) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(painter = icon, contentDescription = null, modifier = Modifier.size(48.dp))
                if (showNewBadge) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Text("New")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.titleSmall)
        }
    }
}