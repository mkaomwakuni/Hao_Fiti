package iz.housing.haofiti.ui.theme.presentation
import android.widget.Space
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.housing.haofiti.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xf9f9f9))) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp))
                .height(360.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.images1),
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
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle favorites action */ }
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = "Share",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                    actionIconContentColor = Color.White
                )
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
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
                tint = Color.Gray,
                modifier = Modifier.size(19.dp)
            )
            Text(
                text = "Surabaya, Mombasa",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Gray),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF0077BE))) {
                        append("Ksh 15000")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("/Month")
                    }
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = "Property Description",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        border = ButtonDefaults.outlinedButtonBorder,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "3 Bedrooms")
                    }
                }
                item {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        border = ButtonDefaults.outlinedButtonBorder,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "2 Guests")
                    }
                }
                item {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        border = ButtonDefaults.outlinedButtonBorder,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "2 Bath")
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
            }
            Spacer(modifier = Modifier.height(10.dp))

            val annotatedString = buildAnnotatedString {
                append("Experience luxurious living in our spacious apartment homes, perfect for modern urban lifestyles. With stunning city views, Enjoy the convenience of our prime location, close to shopping, dining, and entertainment. Discover your new home with us today! ")
                withStyle(style = SpanStyle(color = Color(0xFF0077BE))) {
                    append("Read more")
                }
            }

            Text(
                text =  annotatedString,
                fontSize = 15.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Normal,
                style = TextStyle(color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "LandLord Pic",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Edward Jake",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Owner",
                        color = Color.Gray
                    )
                }
                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = Color.DarkGray),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.message ),
                        tint = Color.White,
                        contentDescription = "Messenger"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
         TextButton(
             modifier = Modifier
                 .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
                 .height(70.dp)
                 .fillMaxWidth(),
             onClick = { /*TODO*/ }) {
             Text(
                 text = "Check Availability",
                 color = Color.White
             )
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
            text = "Suraya Suites",
            fontSize = 24.sp,
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

