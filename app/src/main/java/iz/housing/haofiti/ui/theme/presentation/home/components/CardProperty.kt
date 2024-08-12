package iz.housing.haofiti.ui.theme.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import iz.housing.haofiti.data.model.PropertyItem
import iz.housing.haofiti.viewmodels.HouseViewModel


@Composable
fun CardProperty(property: PropertyItem,houseViewModel: HouseViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                model = property.images.firstOrNull(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(property.name, fontWeight = FontWeight.Bold)
                Text(property.location)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(Icons.Default.Star, contentDescription = null)
                        Text("${property.rating} ratings")
                    }
                    Row {
                        Icon(Icons.Default.LocationOn, contentDescription = null)
                        Text(property.location)
                    }
                    Row {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                        Text(property.name)
                    }
                }
            }
        }
    }
}