package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iz.housing.haofiti.R
import iz.housing.haofiti.ui.theme.presentation.explorer.House

@Composable
fun HouseItem(house: House) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Unspecified
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .width(290.dp)
            .height(260.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = house.imageRes),
                    contentDescription = house.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier.padding(5.dp),
                text = house.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 14.sp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = house.location,
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier.padding(bottom = 10.dp, start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                        style = TextStyle(color = Color.Black, fontSize = 14.sp),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun HouseItemPreview() {
    val house = House("Green Wood Apartments", "London", 4.9, R.drawable.kino)
    HouseItem(house)
}