package iz.housing.haofiti.ui.theme.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import iz.housing.haofiti.R

@Composable
fun TitlePreview(
    title: String,
    rating:Double,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.weight(1F),
            maxLines = 1,
            text = title,
            overflow = TextOverflow.Ellipsis,
            style = textStyle
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(painter = painterResource(id = R.drawable.half_star), contentDescription = "Half_Rating")
        }
    }
}