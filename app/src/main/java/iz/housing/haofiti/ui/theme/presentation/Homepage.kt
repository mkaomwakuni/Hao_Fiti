package iz.housing.haofiti.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iz.housing.haofiti.R

@Composable
fun Homepage() {
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
            title = "New Year Best Deal",
            actionText = "See More",
            actionColor = Color.Blue,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(25.dp))
        HouseList(houses = sampleHouses)
        Spacer(modifier = Modifier.height(40.dp))
        SectionTitle(
            title = "Explore the City",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        CityList(cities = sampleCities)
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearch: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier
            .border(1.dp, color = Color.LightGray)
            .background(Color.LightGray)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
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
fun HouseItem(house: House) {
    Box(modifier = Modifier.width(160.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.BottomStart
            ) {
                ImageFetcher(image = painterResource(id = house.imageRes))
            }
            Text(
                modifier = Modifier.padding(5.dp),
                text = house.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = Color.Black)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = house.location,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = house.rating.toString(),
                        textAlign = TextAlign.End,
                        style = TextStyle(color = Color.Black),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
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
                    .width(100.dp)
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
            .height(160.dp)
            .width(160.dp)
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomepagePreview() {
    Homepage()
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
    House("Green Wood Apartments", "London", 4.9, R.drawable.h_3),
    House("Blue Stone Villa", "Paris", 4.8, R.drawable.h_3),
    House("Sunset Bungalow", "New York", 4.7, R.drawable.villa)
)

val sampleCities = listOf(
    City("Northern Ridge", R.drawable.manchester),
    City("Kasarani", R.drawable.leeds),
    City("Westlands", R.drawable.edinburgh),
    City("Ngara", R.drawable.images1)
)
