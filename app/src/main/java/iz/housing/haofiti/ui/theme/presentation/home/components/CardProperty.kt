package iz.housing.haofiti.ui.theme.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import iz.housing.haofiti.data.model.PropertyItem

@Composable
fun CardProperty(propertyItem: PropertyItem,onItemClick: () -> Unit) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1f)),
                            startY = 0f,
                            endY = 300f
                        )
                    )
            ) {
                AsyncImage(
                    model =  propertyItem.images.firstOrNull(),
                    contentDescription = propertyItem.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ChipSource(
                        text = "${propertyItem.price/1000}K",
                        backgroundColor = MaterialTheme.colorScheme.inverseOnSurface,
                        textColor = Color.Black
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    ChipSource(
                        text = propertyItem.type.name,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                ) {
                    Text(
                        text = propertyItem.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color.White
                    )
                    Text(
                        text = propertyItem.description,
                        maxLines = 1,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

    @Composable
    fun ChipSource(
        text: String,
        backgroundColor: Color,
        textColor: Color
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }