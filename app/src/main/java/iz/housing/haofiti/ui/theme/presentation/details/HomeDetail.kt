package iz.housing.haofiti.ui.theme.presentation.details

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.BarColors
import iz.housing.haofiti.ui.theme.presentation.common.BarEffect
import iz.housing.haofiti.viewmodels.HouseViewModel
import kotlin.math.floor

@Composable
fun HouseDetailsPage(
    navController: NavController,
    viewModel: HouseViewModel,
    propertyId: Int
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val property = uiState.selectedPropertyId

    BarEffect()

    property?.let { propertyItem ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.3f))
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                ImageCarousel(propertyItem, onFavoriteClick = { viewModel.saveProperty(it) })
                HouseDescription(propertyItem)
            }
            DetailSection(
                content = { EnergyEfficiency(propertyItem) }
            )
            DetailSection(
                content = { PropertyDetailsSection(propertyItem) }
            )
            DetailSection(
                content = { LocationAddress(propertyItem) }
            )
            DetailSection(
                content = { AgentDetails(propertyItem, context, navController) }
            )
        }
    }
}

@Composable
fun DetailSection(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            content()
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
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
            IconButton(onClick = {onFavoriteClick()}) {
                if (isSaved) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = "Favorite", tint = Color.Yellow
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
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f)),
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
                    colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colorScheme.primary
                    )
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

@Composable
fun HouseInfo(propertyItem: PropertyItem) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = propertyItem.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "${propertyItem.location}, Kenya",
            color = Color.White
        )
    }
}

@Composable
fun HouseDescription(propertyItem: PropertyItem) {
    var expanded by remember { mutableStateOf(false) }
    val text = propertyItem.description
    val maxLines = 4
    val readMoreText = "Read more \uD83D\uDC40"
    val readLessText = "Read less \uD83D\uDC40"

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                maxLines = if (expanded) Int.MAX_VALUE else maxLines,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = if (expanded) 10.dp else 0.dp)
            )

            if (!expanded) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White),
                                startY = with(LocalDensity.current) { (maxLines * 2).dp.toPx() * 0.8f },
                                endY = with(LocalDensity.current) { (maxLines * 20).dp.toPx() }
                            )
                        )
                )

                Text(
                    text = readMoreText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .clickable { expanded = true }
                        .align(Alignment.BottomCenter)
                        .padding(top = 30.dp)
                )
            } else {
                Text(
                    text = readLessText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .clickable { expanded = false }
                        .align(Alignment.BottomCenter)
                        .padding(top = 2.dp)
                )
            }
        }
    }
}


@Composable
fun PropertyDetailsSection(propertyItem: PropertyItem) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        backgroundColor = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            PropertyDetailRow(painterResource(id = R.drawable.bedroom), "Bedrooms",
                propertyItem.amenities?.bedrooms.toString()
            )
            PropertyDetailRow(painterResource(id = R.drawable.bath), "Bathrooms", propertyItem.amenities?.bathrooms.toString())
            PropertyDetailRow(painterResource(id = R.drawable.homeicon), "Covered area", "180 m²")
            PropertyDetailRow(painterResource(id = R.drawable.bookmark), "Plot area", "320 m²")
            PropertyDetailRow(
                painterResource(id = R.drawable.netflix),
                "Veranda (uncovered)",
                propertyItem.amenities?.verandaCovered.toString()
            )
            PropertyDetailRow(painterResource(id = R.drawable.bookmark), "Furnished", "Fully")
            PropertyDetailRow(painterResource(id = R.drawable.parking), "Parking", propertyItem.amenities?.parking.toString())
            PropertyDetailRow(painterResource(id = R.drawable.ac), "Air-Condition", propertyItem.amenities?.ac.toString())
        }
    }
}

@Composable
fun PropertyDetailRow(icon: Painter, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material.Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun EnergyEfficiencyBar(propertyItem: PropertyItem) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(vertical = 8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BarColors.forEach { color ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(16.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(2.dp)) // Gap between colors
            }
        }
    }
}

@Composable
fun EnergyEfficiency(propertyItem: PropertyItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Energy efficiency",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Certification in progress.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        EnergyEfficiencyBar(propertyItem)
    }
}

@Composable
fun FacilityItem(icon: Painter, text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 14.sp)
    }
}

@Composable
fun LocationAddress(propertyItem: PropertyItem) {
    Box(modifier = Modifier
        .padding(16.dp)
        .border(1.dp, Color.LightGray)){
    Column(modifier = Modifier.padding(5.dp)) {
        // PropertyItem has latitude and longitude properties
        val propertyLocation = LatLng(propertyItem.latitude, propertyItem.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.Builder()
                .target(propertyLocation)
                .zoom(8f)
                .build()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = propertyLocation),
                    title = propertyItem.name,
                    snippet = propertyItem.location
                )
            }
        }
      }
    }
}

@Composable
fun AgentDetails(propertyItem: PropertyItem,context: Context,navController: NavController){
        androidx.compose.material.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Image
                Box(modifier = Modifier.size(80.dp) ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(propertyItem.agent?.corporateImg?.firstOrNull())
                            .build(),
                        contentDescription = "Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }

                // Agent Name
                propertyItem.agent?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                // Agent Role
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = propertyItem.agent?.name.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.LightGray
                    )
                    androidx.compose.material.Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verified",
                        tint = colorResource(id = R.color.verified),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    propertyItem.agent?.rating?.let { Ratings(propertyItem = propertyItem,rating = it) }
                }
                Text(
                    text = propertyItem.agent?.basedLocation.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.LightGray
                )

                // Email Button
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.ocean_blue),
                        contentColor = Color.White
                    )
                ) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Send email",
                        color = Color.White
                    )
                }

                // Phone Button
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.ocean_blue),
                        contentColor = Color.White
                    )
                ) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Show phone",
                        color = Color.White
                    )
                }

                // Agency Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // More from agency link
                    TextButton(
                        onClick = { },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "More from this agency",
                            color = Color(0xFF2196F3),
                            style = MaterialTheme.typography.bodyMedium
                        )
                }
            }
        }
    }
}

@Composable
fun Ratings(propertyItem: PropertyItem, rating: Double,maxStars: Int = 5) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = propertyItem.agent?.rating.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))

        Row {
            val fullStats = floor(rating).toInt()
            val halfStats = (rating - fullStats) >= 0.5

            repeat(fullStats) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(18.dp)
                )
            }

            if (halfStats) {
                Icon(
                    painter = painterResource(R.drawable.half_star),
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(18.dp)
                )
            }

            val borderStars = maxStars - fullStats - (if (halfStats) 1 else 0)
            repeat(borderStars) {
                androidx.compose.material.Icon(
                    painter = painterResource(R.drawable.starborder),
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}