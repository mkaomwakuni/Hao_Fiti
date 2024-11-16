package iz.housing.haofiti.ui.theme.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import iz.housing.haofiti.data.model.Amenities
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.details.toSentenceCase
import java.util.Locale

@Composable
fun PropertyCardHorizontal(property: PropertyItem, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(280.dp)
            .border(0.2f.dp, Color.LightGray)
            .clickable { onItemClick() }
    ) {
        Column(modifier = Modifier
            .padding(2.dp)
            .width(280.dp)) {
            Box {
                AsyncImage(
                    model = property.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = property.type.toString().toSentenceCase())
                Text(
                    text = "Ksh ${property.price}"
                    )

                // Display amenities (bedrooms, bathrooms, etc.)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${property.amenities?.bedrooms} beds ",
                    )
                    VerticalDivider(modifier = Modifier.height(16.dp))
                    Text(
                        text = " ${property.amenities?.bathrooms} baths "
                    )
                    VerticalDivider(modifier = Modifier.height(16.dp))
                    Text(
                        text = " ${property.amenities?.bathrooms} baths "
                    )
                }
                Text(
                    text = "${property.location},Kenya",
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PropertyCardHorizontalPreview() {
//    val property = PropertyItem(
//        name = "Beautiful Apartment",
//        location = "Nairobi",
//        price = 50000,
//        images = listOf("https://via.placeholder.com/350x140"),
//        description = "A beautiful apartment in Nairobi",
//        amenities = Amenities(4, 2, true, true, true, false, true)
//    )
//    PropertyCardHorizontal(property = property, onItemClick = {})
//}
