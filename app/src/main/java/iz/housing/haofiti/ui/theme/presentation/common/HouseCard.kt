package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import iz.housing.haofiti.data.database.HouseInfo

@Composable
fun HouseCard(
    houseInfo: HouseInfo,
    onItemClick: () -> Unit
) {
    Card (
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .width(500.dp)
            .padding(5.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp)),
                    painter = rememberAsyncImagePainter(model = houseInfo.image[0]),
                    contentScale = ContentScale.Crop,
                    contentDescription = "null"
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            TitPreview(title = houseInfo.title, rating = houseInfo.rating)
            Spacer(modifier = Modifier.height(5.dp))
            BarAmenities(
                noOfBedrooms = houseInfo.noOfBedrooms,
                noOfBathrooms = houseInfo.noOfBathrooms,
                areaSize = houseInfo.areaSize
            )
        }
    }
}
@Preview
@Composable
fun HouseCardPreview() {
    HouseCard(houseInfo = HouseInfo()) {}
}