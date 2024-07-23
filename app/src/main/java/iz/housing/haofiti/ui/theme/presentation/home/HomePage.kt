package iz.housing.haofiti.ui.theme.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.viewmodels.HouseViewModel


@Composable
fun HomePage(viewModel: HouseViewModel,navController: NavController) {
    val scroll = rememberScrollState()
    var searchQuery by remember { mutableStateOf("") }
    val properties by viewModel.properties.collectAsState()
    val location by viewModel.locations.collectAsState()
    Scaffold(
        bottomBar = {BottomNavComponent(navController = navController)},
    ) { paddingValue ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValue).verticalScroll(scroll)) {
            val annotedText = buildAnnotatedString {
                pushStyle(SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                append("Let's find your\n")
                pushStyle(SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                append("dream home.")
                addStyle(SpanStyle(color = MaterialTheme.colorScheme.primary), 15, 26)
                pop()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(start = 16.dp, end = 16.dp, top = 30.dp)
            ) {
                Text(
                    text = "urban.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                BasicText(text = annotedText)
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar1(searchQuery = searchQuery, onSearchQueryChange = { searchQuery = it })
                Spacer(modifier = Modifier.height(16.dp))
                PreviouslyViewedSection(properties)
                Spacer(modifier = Modifier.height(16.dp))
                RecommendationsSection(properties)
            }
        }
    }
}

@Composable
fun SearchBar1(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search any city, area, landmark...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.LightGray.copy(alpha = 0.3f),
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun PreviouslyViewedSection(properties: List<PropertyItem>) {
    Column {
        Text(
            text = "Previously Viewed",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(properties.take(2)) { property ->
                PropertyCardHorizontal(property)
            }
        }
    }
}

@Composable
fun RecommendationsSection(properties: List<PropertyItem>) {
    Column {
        Text(
            text = "Our Recommendations",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            properties.forEach{ property ->
                PropertyCardVertical(property)
            }
        }
    }
}


@Composable
fun PropertyCardHorizontal(property: PropertyItem) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = 4.dp
    ) {
        Column {
            Box {
                AsyncImage(
                    model = property.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .size(24.dp),
                    onClick = {}) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Save")
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = property.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Location", tint = Color.Gray, modifier = Modifier.size(18.dp))
                    Text(text = property.location, color = Color.Gray, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Ksh ${property.id}/month", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun PropertyCardVertical(property: PropertyItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.height(220.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)) {
                AsyncImage(
                    model = property.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.FillBounds
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .size(24.dp),
                    onClick = {}) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Save")
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = property.name, fontWeight = FontWeight.Bold)
                    Text(text = "Ksh ${property.id}/month",fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Location", tint = Color.Gray, modifier = Modifier.size(15.dp))
                    Text(text = property.location, color = Color.Gray, fontSize = 12.sp)
                }
            }
        }
    }
}

