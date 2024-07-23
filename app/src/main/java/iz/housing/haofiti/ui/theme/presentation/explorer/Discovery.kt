package iz.housing.haofiti.ui.theme.presentation.explorer

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.R
import iz.housing.haofiti.ui.theme.presentation.common.BottomNavComponent
import iz.housing.haofiti.ui.theme.presentation.common.HouseItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Discovery(navController: NavController) {
    Scaffold(
        bottomBar = {BottomNavComponent (navController = navController)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomHeaderTitle()
            Spacer(modifier = Modifier.height(30.dp))
            var searchText by remember { mutableStateOf("") }
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                searchText = searchText,
                onSearch = { searchText = it }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SectionTitle(
                title = "Best Deals",
                actionText = "See More",
                actionColor = Color.Blue,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))
            HouseList(houses = sampleHouses)
            Spacer(modifier = Modifier.height(60.dp))
            SectionTitle(
                title = "Explore the City",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            CityList(cities = sampleCities)
        }
    }
}

@Composable
fun CustomHeaderTitle(
    modifier: Modifier = Modifier,
    fontSize: Int = 27
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = fontSize.sp)) {
            append("Find the ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)) {
            append("best ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)) {
            append("place to stay")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = fontSize.sp)) {
            append(" and ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)) {
            append("live with family")
        }
    }

    ClickableText(
        modifier = modifier.padding(top = 25.dp),
        text = annotatedString,
        onClick = {},
        style = TextStyle(color = Color.Black)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearch: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface {
        TextField(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.LightGray.copy(alpha = 0.3f),
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = onSearch,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(searchText)
                keyboardController?.hide()
            }),
            trailingIcon = {
                IconButton(onClick = { onSearch(searchText) }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            placeholder = { Text(text = "Find \"Ball\"") }
        )
    }
}

@Composable
fun SectionTitle(
    title: String,
    actionText: String? = null,
    actionColor: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = title
        )
        if (actionText != null) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                color = actionColor,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                text = actionText
            )
        }
    }
}

@Composable
fun HouseList(houses: List<House>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(houses.size) { index ->
            HouseItem(house = houses[index])
        }
    }
}



@Composable
fun CityList(cities: List<City>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(cities.size) { index ->
            CityItem(city = cities[index])
        }
    }
}

@Composable
fun CityItem(city: City) {
    Box(modifier = Modifier.width(100.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(220.dp)
                    .height(100.dp)
                    .padding(5.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.BottomStart
            ) {
                CircleCityImage(city = painterResource(id = city.imageRes))
            }
            Text(
                modifier = Modifier.padding(5.dp),
                text = city.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = Color.Black)
            )
        }
    }
}

@Composable
fun ImageFetcher(image: Painter) {
    Image(
        modifier = Modifier
            .height(180.dp)
            .width(220.dp)
            .clip(RoundedCornerShape(5.dp)),
        painter = image,
        contentScale = ContentScale.FillBounds,
        contentDescription = "Image"
    )
}

@Composable
fun CircleCityImage(city: Painter) {
    Image(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp),
        painter = city,
        contentScale = ContentScale.FillBounds,
        contentDescription = "Image"
    )
}

@Preview( showBackground = true)
@Composable
fun HomepagePreview() {
    Discovery(rememberNavController())
}

data class House(
    val name: String,
    val location: String,
    val rating: Double,
    val imageRes: Int
)

data class City(
    val name: String,
    val imageRes: Int
)

val sampleHouses = listOf(
    House("Green Wood Apartments", "London", 4.9, R.drawable.kino),
    House("Blue Stone Villa", "Paris", 4.8, R.drawable.kiambu),
    House("Sunset Bungalow", "New York", 4.7, R.drawable.lavington)
)

val sampleCities = listOf(
    City("Northern Ridge", R.drawable.ngara),
    City("Kasarani", R.drawable.westlands),
    City("Westlands", R.drawable.kileleshwa),
    City("Ngara", R.drawable.lavington)
)
