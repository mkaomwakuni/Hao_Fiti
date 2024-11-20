package iz.housing.haofiti.ui.theme.presentation.home.components

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import iz.housing.haofiti.data.model.Amenities
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.ui.theme.presentation.navigation.Route

@Composable
fun PropertyCard(property: PropertyItem,onItemClick: () -> Unit) {
    androidx.compose.material.Card(
        modifier = Modifier
            .width(340.dp)
            .clickable { onItemClick() }
            .shadow(1.dp),
        shape = RectangleShape,
        elevation = 4.dp,
    ) {
        Column {
            Box {
                AsyncImage(
                    model = property.images.firstOrNull(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .size(24.dp),
                    onClick = {}) {
                    androidx.compose.material.Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Save"
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    androidx.compose.material.Text(
                        text = property.name,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    androidx.compose.material.Text(
                        text = "Ksh ${property.price/1000}K /month",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    androidx.compose.material.Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                    androidx.compose.material.Text(
                        text = property.location,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
//                @Preview(showBackground = true)
//                @Composable
//                fun PropertyCardHorizontalPreview1() {
//                    val property = PropertyItem(
//                        name = "Beautiful Apartment",
//                        location = "Nairobi",
//                        price = 50000,
//                        images = listOf("https://via.placeholder.com/350x140"),
//                        description = "A beautiful apartment in Nairobi",
//                        amenities = Amenities(4, 2, true, true, true, false, true)
//                    )
//                    PropertyCard(property = property, onItemClick = {})
//                }