package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import iz.housing.haofiti.ui.theme.presentation.explorer.House
import iz.housing.haofiti.ui.theme.presentation.explorer.ImageFetcher

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