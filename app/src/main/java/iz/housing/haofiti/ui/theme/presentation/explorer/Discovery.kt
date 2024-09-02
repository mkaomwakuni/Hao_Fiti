package iz.housing.haofiti.ui.theme.presentation.explorer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.Towns
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.common.CircularShimmerEffect
import iz.housing.haofiti.ui.theme.presentation.home.LoadingEffect
import iz.housing.haofiti.ui.theme.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun Discovery(
    state: HouseStates,
    navController: NavController,
    onItemClick: (PropertyItem) -> Unit,
    onEvent: (HouseEvent) -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavComponent(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            PropertyTypes()
            Spacer(modifier = Modifier.height(5.dp))
            SectionHeader("Properties Nearby")
            PropertiesSection(state, onItemClick, onEvent, navController)
            Spacer(modifier = Modifier.height(2.dp))
            SectionHeader("Explore Locations")
            LocationsSection(state.isLoading)
        }
    }
}

@Composable
fun PropertyTypes() {
    Text(
        text = "Your \nGateway to \nAffordable \nHousing",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp),
        textAlign = TextAlign.Start
    )
    val types = listOf("Bungalow", "Apartment", "Villa", "Rentals")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(types) { type ->
            PropertyTypeChip(type)
        }
    }
}

@Composable
fun PropertyTypeChip(type: String) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .width(85.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            .background(Color.Transparent, RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = getIconForType(type),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(type, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun getIconForType(type: String): Painter = painterResource(
    when (type) {
        "Bungalow" -> R.drawable.bungalow
        "Apartment" -> R.drawable.apartments
        "Villa" -> R.drawable.rental
        else -> R.drawable.home
    }
)

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun PropertiesSection(
    state: HouseStates,
    onItemClick: (PropertyItem) -> Unit,
    onEvent: (HouseEvent) -> Unit,
    navController: NavController
) {
    if (state.isLoading) {
        LoadingEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
                .padding(16.dp)
        )
    } else {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(state.properties) { property ->
                PropertyCardDiscover(
                    property = property,
                    onItemClick = {
                        onEvent(HouseEvent.OnCardClicked(property))
                        navController.navigate(Route.HouseDetails.createRoute(property.id))
                    }
                )
            }
        }
    }
}

@Composable
fun PropertyCardDiscover(property: PropertyItem, onItemClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(310.dp)
            .scale(scale)
            .background(Color.White, RoundedCornerShape(12.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { isPressed = true; tryAwaitRelease(); isPressed = false },
                    onTap = { onItemClick() }
                )
            }
    ) {
        Column {
            PropertyImage(property)
            PropertyInfoSection(property)
        }
    }
}

@Composable
fun PropertyImage(property: PropertyItem) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(210.dp)
    ) {
        AsyncImage(
            model = property.images.firstOrNull(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 3.dp)
        ) {
            PropertyFeaturesRow(property)
        }
    }
}

@Composable
fun PropertyFeaturesRow(property: PropertyItem) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf(
            Triple(R.drawable.bedroom, property.amenities?.bedrooms.toString(), "Bedrooms"),
            Triple(R.drawable.bath, property.amenities?.bathrooms.toString(), "Bathrooms"),
            Triple(R.drawable.vwifi, if (property.amenities?.wifi == true) "Yes" else "No", "WiFi"),
            Triple(R.drawable.half_star, property.rating.toString(), "Rating")
        ).forEachIndexed { index, (icon, text, contentDescription) ->
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandHorizontally(),
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f),
            ) {
                PropertyFeatureChip(
                    icon = painterResource(id = icon),
                    text = text,
                    contentDescription = contentDescription
                )
            }
        }
    }
}

@Composable
fun PropertyInfoSection(property: PropertyItem) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(property.name, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "${property.location}, Kenya",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "${property.price / 1000}Ksh/month",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun PropertyFeatureChip(icon: Painter, text: String, contentDescription: String) {
    AssistChip(
        onClick = { /* Add action if necessary */ },
        label = { Text(text, style = MaterialTheme.typography.bodySmall) },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(12.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        )
    )
}

@Composable
fun LocationsSection(isLoading: Boolean) {
    if (isLoading) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) {
                CircularShimmerEffect(modifier = Modifier.size(100.dp))
            }
        }
    } else {
        TownsAndCities()
    }
}

@Composable
fun TownsAndCities() {
    var selectedTownIndex by remember { mutableStateOf<Int?>(null) }
    val towns = listOf(
        Towns("Kiambu", R.drawable.kiambu),
        Towns("Westlands", R.drawable.westlands),
        Towns("Lavington", R.drawable.lavington),
        Towns("Kileleshwa", R.drawable.kileleshwa),
        Towns("Ngara", R.drawable.ngara)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        itemsIndexed(towns) { index, town ->
            LocationItem(
                townName = town.name,
                imageResId = town.imagesRes,
                isSelected = selectedTownIndex == index,
                onClick = { selectedTownIndex = index },
                animationDelay = selectedTownIndex?.let { abs(index - it) * 100 } ?: 0
            )
        }
    }
}

@Composable
fun LocationItem(
    townName: String,
    imageResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    animationDelay: Int
) {
    var animationTriggered by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            animationTriggered = false
            delay(animationDelay.toLong())
            animationTriggered = true
        }
    }

    val animatedSize by animateFloatAsState(
        targetValue = when {
            !isSelected -> 1f
            animationTriggered -> 1.1f
            else -> 1f
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .scale(animatedSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = townName,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}