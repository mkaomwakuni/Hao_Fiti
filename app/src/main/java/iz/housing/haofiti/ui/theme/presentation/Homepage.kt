package iz.housing.haofiti.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.housing.haofiti.R

@Composable
fun Homepage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp , end = 14.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomHeaderTitle()

        Spacer(modifier = Modifier.height(30.dp))

        // Handle search
        SearchBar(modifier = Modifier.fillMaxWidth(), searchText = "") {

        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = "New  Year Best Deal"
            )
            Spacer(modifier = Modifier.width(115.dp))
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                color = Color.Blue,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                text = "See More"
            )
        }
        Spacer(modifier = Modifier.height(25.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Box(modifier = Modifier.width(160.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
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
                            ImageFetcher(image = painterResource(id = R.drawable.h_3))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Green Wood Apartments",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                textAlign = TextAlign.Start,
                                text = "London",
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
                                    text = "4.9",
                                    textAlign = TextAlign.End,
                                    style = TextStyle(color = Color.Black),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.width(160.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
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
                            ImageFetcher(image = painterResource(id = R.drawable.h_3))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Green Wood Apartments",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                textAlign = TextAlign.Start,
                                text = "London",
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
                                    text = "4.9",
                                    textAlign = TextAlign.End,
                                    style = TextStyle(color = Color.Black),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.width(160.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
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
                            ImageFetcher(image = painterResource(id = R.drawable.villa))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Green Wood Apartments",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                textAlign = TextAlign.Start,
                                text = "London",
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
                                    text = "4.9",
                                    textAlign = TextAlign.End,
                                    style = TextStyle(color = Color.Black),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
            .fillMaxWidth()) {
            Text(
                textAlign = TextAlign.Start,
                text = "Explore the City",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            item {
                Box(modifier = Modifier.width(100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .padding(5.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            CircleCityImage(city = painterResource(id = R.drawable.manchester))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Northern Ridge",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black))
                        }
                    }
                }
            item {
                Box(modifier = Modifier.width(100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .padding(5.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            CircleCityImage(city = painterResource(id = R.drawable.leeds))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Kasarani",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black))
                    }
                }
            }
            item {
                Box(modifier = Modifier.width(100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .padding(5.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            CircleCityImage(city = painterResource(id = R.drawable.edinburgh))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Westlands",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black))
                    }
                }
            }
            item {
                Box(modifier = Modifier.width(100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .padding(5.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            CircleCityImage(city = painterResource(id = R.drawable.images1))
                        }

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Ngara",
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black))
                    }
                }
            }
            }

        }
    }

@Composable
fun CustomHeaderTitle(
    modifier: Modifier = Modifier,
    fontSize: Int = 27
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = fontSize.sp)
        ) {
            append("Find the ")
        }
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
        ) {
            append("best ")
        }
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
        ) {
            append("place to stay")
        }
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = fontSize.sp)
        ) {
            append(" and ")
        }
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
        ) {
            append("live with family")
        }
    }

    ClickableText(
        modifier = modifier.padding(top = 25.dp),
        text = annotatedString,
        onClick = {
        },
        style = TextStyle(color = Color.Black)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    searchText: String,
    onSearch: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier.clip(shape = RoundedCornerShape(10.dp)),
        color = Color.LightGray,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { text ->
                onSearch(text)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(searchText)
                keyboardController?.hide()
            }),
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearch(searchText)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            placeholder = {
                Text(text = """Find"Ball""")
            }
        )
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
fun CircleCityImage(city :Painter){
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
fun HomepagePreview(){
    Homepage()
}