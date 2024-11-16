package iz.housing.haofiti.ui.theme.presentation.home

import android.Manifest
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.model.Towns
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.common.CardShimmerEffect
import iz.housing.haofiti.ui.theme.presentation.common.CircularShimmerEffect
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCard
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCardHorizontal
import iz.housing.haofiti.ui.theme.presentation.navigation.Route
import iz.housing.haofiti.viewmodels.HouseViewModel
import kotlinx.coroutines.delay
import kotlin.math.abs

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomePage(
    state: HouseStates,
    navController: NavController,
    onItemClick: (PropertyItem) -> Unit,
    onEvent: (HouseEvent) -> Unit,
    viewModel: HouseViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val currentLocation = uiState.currentLocation
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (locationPermissionState.status.isGranted) {
            viewModel.fetchCurrentLocation(context)
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Scaffold(bottomBar = { BottomNavComponent(navController = navController) }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                        text = "$currentLocation",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    GreetingText()
                    Spacer(modifier = Modifier.height(14.dp))
                    SearchBarHome(navController)
                    Spacer(modifier = Modifier.height(14.dp))
                    SectionTitle(stringResource(R.string.previously))
                }
            }

            item {
                if (state.isLoading) LoadingEffect()
                else PropertyRow(
                    state.properties,
                    navController,
                    onEvent
                )
            }

            item {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Spacer(modifier = Modifier.height(14.dp))
                    SectionTitle(stringResource(R.string.recommendations))
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            item {
                if (state.isLoading) LoadingEffect()
                else PropertyRowSales(
                    state.properties,
                    navController,
                    onEvent
                )
            }

            item {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    SectionTitle(stringResource(R.string.Trends))
                    SectionSubTitle(stringResource(R.string.subCity))
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            item(5){
                if (state.isLoading)  CircularShimmerEffect(modifier = Modifier.size(100.dp))
                else {
                    TownsAndCities()
                }
            }
        }
    }
}

@Composable
fun GreetingText() {
    Text(
        text = buildAnnotatedString {
            append("Let's find your\n")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                append("dream home.")
            }
        },
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SearchBarHome(navController: NavController) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { navController.navigate(Route.Search.route) },
        placeholder = { Text(stringResource(R.string.label)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Transparent),
        shape = RoundedCornerShape(8.dp),
        enabled = false
    )
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SectionSubTitle(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue)
}

@Composable
fun LoadingEffect(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().wrapContentHeight()) {
        repeat(3) {
            CardShimmerEffect(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        }
    }
}

@Composable
fun PropertyRow(
    properties: List<PropertyItem>,
    navController: NavController,
    onEvent: (HouseEvent) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(properties) { property ->
            Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
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

@Composable
fun PropertyRowSales(
    properties: List<PropertyItem>,
    navController: NavController,
    onEvent: (HouseEvent) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(properties) { property ->
            Box(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                PropertyCardHorizontal(
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
