package com.example.homehive.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homehive.R
import com.example.homehive.data.PropertyInfo
import com.example.homehive.data.propertyList
import com.example.homehive.presentation.comman.CardPropertyHighlights
import com.example.homehive.presentation.comman.CardTitleAndRating
import coil.compose.rememberAsyncImagePainter

@Composable
fun PropertyCardVer(
    propertyInfo: PropertyInfo,
    onItemClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .width(500.dp)
            .padding(4.dp)
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    painter = rememberAsyncImagePainter(propertyInfo.images[0]),
                    contentDescription = "null",
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_like_outlined),
                        contentDescription = "Favorite Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(8.dp),

                        )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            CardTitleAndRating(title = propertyInfo.name, rating = propertyInfo.rating)

            Spacer(modifier = Modifier.height(8.dp))

            CardPropertyHighlights(
                noOfBedroom = propertyInfo.noOfBedrooms,
                noOfBathroom = propertyInfo.noOfBathroom,
                areaStFt = propertyInfo.areaSqFt
            )
        }
    }

}

@Preview
@Composable
fun PropertyCardPreview() {
    PropertyCardVer(propertyInfo = propertyList[0], onItemClick = {})
}