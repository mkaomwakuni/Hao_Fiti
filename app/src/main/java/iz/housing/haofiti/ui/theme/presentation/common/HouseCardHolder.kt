package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import iz.housing.haofiti.R
import iz.housing.haofiti.data.database.HouseInfo

@Composable
fun HouseCardHolder(
    houseInfo: HouseInfo,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = Color.White,
        modifier = Modifier
            .padding(5.dp)
            .clickable(onClick = onClick),
        shadowElevation = 4.dp
    ) {
        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            Image(modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(10)),
                painter = rememberAsyncImagePainter(model = houseInfo.image[0]),
                contentDescription = "null",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                TitlePreview(title = houseInfo.title, rating = houseInfo.rating)
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Icon(painter = painterResource(id = R.drawable.location), contentDescription = "Location")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = houseInfo.location, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.width(5.dp))
                    BarAmenities(noOfBedrooms = houseInfo.noOfBedrooms, noOfBathrooms = houseInfo.noOfBathrooms, areaSize = houseInfo.areaSize)
                }
            }
        }

    }
}