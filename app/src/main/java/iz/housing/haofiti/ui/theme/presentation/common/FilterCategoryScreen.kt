package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import iz.housing.haofiti.ui.theme.presentation.navigation.NavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCategoryScreen(navController: NavController, categoryTitle: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors( MaterialTheme.colorScheme.background ),
                title = { Text(text = categoryTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 16.dp)
            ){
                items(houseCollectiveList.size) { index ->
                    HouseCardHolder(
                        houseInfo = houseCollectiveList[index],
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "houseInfo",
                                value = houseCollectiveList[index]
                            )
                            navController.navigate(NavScreens.Details.route)
                        },
                    )
                }
             }
        }
    }

}