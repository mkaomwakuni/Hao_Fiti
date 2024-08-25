package iz.housing.haofiti.ui.theme.presentation.explorer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.home.LoadingEffect
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCard
import iz.housing.haofiti.ui.theme.presentation.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun Discovery(
    state: HouseStates,
    navController: NavController,
    onItemClick: (PropertyItem) -> Unit,
    onEvent: (HouseEvent) -> Unit
) {
    var animationLoading by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = { BottomNavComponent(navController = navController) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            item { PropertyTypes() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                SectionHeader(
                    title = "Property nearby",
                    onSeeMoreClick = { /* Handle See More */ }
                )
            }

            item {
                LaunchedEffect(true) {
                    delay(1000)
                    animationLoading = false
                }

                if (animationLoading) {
                    LoadingEffect()
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

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                SectionHeader(
                    title = "Recently Booked",
                    onSeeMoreClick = { /* Handle See More */ }
                )
            }

            items(state.properties) { property ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    PropertyCard(
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
}

@Composable
fun PropertyTypes() {
    Text(
        text = " Your\n Gateway to \n Affordable\n Housing",
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        textAlign = TextAlign.Start
    )
    val types = listOf("Bungalow", "Apartment", "Villa", "Rentals")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(types.size) { index ->
            PropertyTypeChip(type = types[index])
        }
    }
}

@Composable
fun PropertyTypeChip(type: String) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .width(85.dp)
            .border(
                color = MaterialTheme.colorScheme.primary,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Transparent,
                shape = RoundedCornerShape(12.dp)
            )
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
            Text(type, fontSize = 12.sp)
        }
    }
}

@Composable
fun getIconForType(type: String): Painter {
    return when (type) {
        "Bungalow" -> painterResource(id = R.drawable.bungalow)
        "Apartment" -> painterResource(id = R.drawable.apartments)
        "Villa" -> painterResource(id = R.drawable.rental)
        else -> painterResource(id = R.drawable.home)
    }
}

@Composable
fun SectionHeader(title: String, onSeeMoreClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            text = "See More",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onSeeMoreClick() }
        )
    }
}

@Composable
fun PropertyCardDiscover(property: PropertyItem, onItemClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(350.dp)
            .scale(scale)
            .alpha(alpha)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = { onItemClick() }
                )
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(250.dp)
            ) {
                AsyncImage(
                    model = property.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop
                )
                PropertyFeaturesRow(
                    property = property,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 6.dp)
                )
            }
            PropertyInfoSection(property = property)
        }
    }
}

@Composable
fun PropertyFeaturesRow(modifier: Modifier = Modifier, property: PropertyItem) {
        var visible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            visible = true
        }

        Row(
            modifier = modifier
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
                    enter = fadeIn() + expandHorizontally()
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
        Text(property.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("${property.location}, Kenya", color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("${property.price / 1000}Ksh/month", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun PropertyFeatureChip(icon: Painter, text: String, contentDescription: String) {
    AssistChip(
        onClick = { /* Add action if necessary */ },
        label = { Text(text, fontSize = 12.sp) },
        border = null,
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(12.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.White.copy(alpha = 0.7f)
        )
    )
}
