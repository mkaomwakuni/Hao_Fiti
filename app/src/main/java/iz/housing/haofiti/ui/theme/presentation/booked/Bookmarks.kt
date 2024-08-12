package iz.housing.haofiti.ui.theme.presentation.booked

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import iz.housing.haofiti.R
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.home.components.CardProperty
import iz.housing.haofiti.viewmodels.HouseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    houseViewModel: HouseViewModel = viewModel(),
    navController: NavController
) {
    val bookmarkedProperties by houseViewModel.savedPropertyListings.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.Favourites),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            BottomNavComponent(navController = navController)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (bookmarkedProperties.isEmpty()) {
                Text(
                    "No bookmarked properties",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn {
                    items(bookmarkedProperties) { property ->
                        CardProperty(property, houseViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun BookmarkedList(savedProperty: List<PropertyItem>, houseViewModel: HouseViewModel, navController: NavController) {
    LazyColumn {
        items(savedProperty) { property ->
            CardProperty(property,houseViewModel)
        }
    }
}