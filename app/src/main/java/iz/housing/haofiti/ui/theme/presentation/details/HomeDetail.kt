package iz.housing.haofiti.ui.theme.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.common.BarEffect
import iz.housing.haofiti.viewmodels.HouseViewModel

@Composable
fun HouseDetailsPage(
    navController: NavController,
    viewModel: HouseViewModel,
    propertyId: Int
) {
    val uiState by viewModel.uiState.collectAsState()

    val property = uiState.selectedPropertyId

    BarEffect()

    property?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ImageCarousel(property, onFavoriteClick = { viewModel.saveProperty(it) })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
            ) {
                HouseDescription(property)
                HouseFacilities(property)
                LocationAddress(property)
                AgentDetails(property)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageTopBar(
    isSaved:Boolean,
    onFavoriteClick: () -> Unit) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle share */ }) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
            IconButton(onClick = {onFavoriteClick()}) {
                if (isSaved) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = "Favorite"
                    )
                }else{
                    Icon(
                        Icons.Default.FavoriteBorder, contentDescription = "Removed Favorite"
                    )
                }
            }
        }
        , colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCarousel(propertyItem: PropertyItem,onFavoriteClick: (PropertyItem) -> Unit) {
    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(propertyItem.images.firstOrNull())
                .build(),
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 0f,
                        endY = 250f
                    )
                )
        )

        HomepageTopBar(
            isSaved = propertyItem.isSaved,
            onFavoriteClick = {
                onFavoriteClick(propertyItem)
            }
        )
        Column (
            modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, top = 180.dp),
            verticalArrangement = Arrangement.Bottom){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp ,top = 20.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Chip(
                    onClick = { },
                    colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text(propertyItem.type.name.toSentenceCase(), color = Color.White)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Chip(
                    onClick = { },
                    colors = ChipDefaults.chipColors(backgroundColor = Color.White)
                ) {
                    Icon(Icons.Default.ThumbUp, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Recommended")
                }
            }
            HouseInfo(propertyItem)
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            repeat(propertyItem.images.size.coerceAtMost(5)) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (index == 0) Color.White else Color.Gray.copy(alpha = 0.5f))
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}

fun String.toSentenceCase(): String {
    if (this.isEmpty()) return this
    return this[0].uppercaseChar() + this.substring(1).lowercase()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseInfo(propertyItem: PropertyItem) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = propertyItem.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text(text = propertyItem.location, color = Color.White)
    }
}

@Composable
fun HouseDescription(propertyItem: PropertyItem) {
    val text = propertyItem.description
    val readMoreText = "Read more"

    val annotatedString = buildAnnotatedString {
        append(text)
        append(" ")
        pushStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline))
        append(readMoreText)
        pop()
        addStringAnnotation(tag = "ReadMore", annotation = "https://example.com", start = text.length + 1, end = text.length + 1 + readMoreText.length)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "ReadMore", start = offset, end = offset)
                    .firstOrNull()?.let {
                        // Handle read more click
                    }
            },
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
        )
    }
}

@Composable
fun HouseFacilities(propertyItem: PropertyItem) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)) {
        Text("Our Facilities", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(4.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            // Left column
            Column(modifier = Modifier.weight(1f)) {
                FacilityItem(painterResource(R.drawable.vwifi), "Wi-Fi available")
                FacilityItem(painterResource(R.drawable.thermometer), "Hot water")
                FacilityItem(painterResource(R.drawable.netflix), "Netflix, Spotify..")
                FacilityItem(painterResource(R.drawable.ac), "AC - ductless split")
            }

            // Right column
            Column(modifier = Modifier.weight(1f)) {
                FacilityItem(painterResource(R.drawable.kitchen), "Kitchen")
                FacilityItem(painterResource(R.drawable.bedroom), "4 bedrooms")
                FacilityItem(painterResource(R.drawable.parking), "Free parking lot")
                FacilityItem(painterResource(R.drawable.bookmark), "Many more facilities")
            }
        }
    }
}

@Composable
fun FacilityItem(icon: Painter, text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 14.sp)
    }
}

@Composable
fun LocationAddress(propertyItem: PropertyItem) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text("Location", fontWeight = FontWeight.Bold, fontSize = 18.sp,style = MaterialTheme.typography.bodyLarge)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        )
    }
}
@Composable
fun AgentDetails(propertyItem: PropertyItem){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(propertyItem.agent?.name ?: "", fontWeight = FontWeight.Bold, fontSize = 20.sp,style = MaterialTheme.typography.bodyMedium)
        Column(modifier = Modifier.border(width = 1.dp, color = Color.LightGray)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.realestate),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = { /* TODO: Handle call */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            ) {
                Icon(Icons.Outlined.Call, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Call Agent", fontSize = 16.sp, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = { /* Prompt to Chat */ },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
            ) {
                Icon(Icons.Outlined.MailOutline, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Message",fontSize = 16.sp, color = Color.White)
            }
        }
    }
}