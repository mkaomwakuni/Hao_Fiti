package iz.housing.haofiti.ui.theme.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.HouseStates
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.data.service.HouseEvent
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.common.CardShimmerEffect
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCard
import iz.housing.haofiti.ui.theme.presentation.home.components.PropertyCardHorizontal
import iz.housing.haofiti.ui.theme.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    state: HouseStates,
    navController: NavController,
    onItemClick: (PropertyItem) -> Unit,
    onEvent: (HouseEvent) -> Unit) {

    var animationLoading  by remember { mutableStateOf(true)}

    Scaffold(
        bottomBar = {BottomNavComponent(navController = navController)},
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)) {
                val annotedText = buildAnnotatedString {
                pushStyle(SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                append("Let's find your\n")
                pushStyle(SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                append("dream home.")
                addStyle(SpanStyle(color = MaterialTheme.colorScheme.primary), 15, 26)
                pop()
            }

            LaunchedEffect(Unit) {
                launch {
                    delay(1000)
                    animationLoading = false
                }
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
                SearchBarHome(
                    navController = navController,
                    searchQuery = "",
                    onSearchQueryChange = {},
                    )
                Spacer(modifier = Modifier.height(16.dp))
                TextSection(stringResource(R.string.previously))
                if (state.isLoading) {
                    LoadingEffect()
                } else {
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.properties) { property ->
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
                    Spacer(modifier = Modifier.height(16.dp))
                    TextSection(stringResource(R.string.recommendations))
                    Spacer(modifier = Modifier.height(16.dp))
                if (state.isLoading) {
                    LoadingEffect()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(2.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.properties) { property ->
                            PropertyCard(
                                property = property,
                                onItemClick = {
                                    onEvent(
                                        HouseEvent.OnCardClicked(property)
                                    )
                                    navController.navigate(Route.HouseDetails.createRoute(property.id))
                                })
                            }
                        }
                    }
                }
            }
            LaunchedEffect(true) {
                delay(1000)
                animationLoading = false
            }
        }
    }

@Composable
fun SearchBarHome(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    navController: NavController) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Route.Search.route)
            },
        placeholder = { Text(stringResource(R.string.label)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = false
    )
}
@Composable
fun TextSection(text: String) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
}
@Composable
fun LoadingEffect(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        repeat(3) {
            CardShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
    }
}