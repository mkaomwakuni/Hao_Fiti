import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haofiti.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.kino),
                contentDescription = "null"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourite",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }
            TopAppBar(
                title = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 10.dp),
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle favorites action */ }
                    ) {
                        Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                    actionIconContentColor = Color.White
                )
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        HouseName()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(19.dp)
            )
            Text(
                text = "Surabaya, East Java, Indonesia",
                textAlign = TextAlign.End,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.LightGray),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
            Text(
                text = "Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black),

            )
            LazyRow(
                modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    Text(
                        text = "3 Bedrooms",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        style = TextStyle(color = Color.LightGray))
                }
                item {
                    Text(
                        text = "2 Guests",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        style = TextStyle(color = Color.LightGray))
                }
                item {
                    Text(
                        text = "2 Bath",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        style = TextStyle(color = Color.LightGray)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(colorResource(id = R.color.Graywhite)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.park), contentDescription = null, tint = Color.Black)
                        Text("Parking", color = Color.Black)
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(colorResource(id = R.color.Graywhite)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.towel), contentDescription = null, tint = Color.Black)
                        Text("Towel", color = Color.Black)
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(colorResource(id = R.color.Graywhite)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.wifi), contentDescription = null, tint = Color.Black)
                        Text("Wi-Fi", color = Color.Black)
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(colorResource(id = R.color.Graywhite)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.tv), contentDescription = null, tint = Color.Black)
                        Text("Television", color = Color.Black)
                    }

                }

                }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Experience luxurious living in our spacious apartment homes, perfect for modern urban lifestyles. With stunning city views, our apartments offer a blend of comfort and style. Enjoy the convenience of our prime location, close to shopping, dining, and entertainment. Discover your new home with us today!",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = TextStyle(color = Color.DarkGray)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = { /* Handle price button click */ },
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    Text(
                        text = "$129/Night",
                        fontSize = 18.sp,
                        color = Color.Black)
                }
                TextButton(
                    onClick = { /* Handle book now button click */ },
                    modifier = Modifier
                        .background(Color.Blue)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("Book Now")
                }
            }

        }
        }
    }


@Composable
fun HouseName() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Start,
            text = "56th Avenue Suites",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = Color.Black),
            modifier = Modifier.padding(start = 10.dp)
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
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = "4.9",
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                style = TextStyle(color = Color.Black),
                modifier = Modifier.padding(start = 4.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    Details()
}


