package iz.housing.haofiti.ui.theme.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iz.housing.haofiti.R

@Composable
fun HouseDetailsPage(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImageCarousel()
            HouseDescription()
            HouseFacilities()
            LocationAddress()
            AgentDetails()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageTopBar() {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Handle share */ }) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
            IconButton(onClick = { /* TODO: Handle favorite */ }) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
            }
        }
        , colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCarousel() {
    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.lavington), // Replace with actual image resource
            contentDescription = "Villa Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        HomepageTopBar()
        Column (
            modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, top = 180.dp),
            verticalArrangement = Arrangement.Bottom){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp ,top = 20.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Chip(
                    onClick = { },
                    colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text("Villa", color = Color.White)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Chip(
                    onClick = { },
                    colors = ChipDefaults.chipColors(backgroundColor = Color.White)
                ) {
                    Icon(Icons.Default.ThumbUp, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Recommended")
                }
            }
            HouseInfo()
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (index == 0) Color.White else Color.Gray.copy(alpha = 0.5f))
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseInfo() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Park Residence Villa", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text("Malang, East Java, Indonesia", color = Color.White)
    }
}

@Composable
fun HouseDescription() {
    val text = "Park Residence Villa offers an exceptional villa rental experience, where luxury and tranquility blend harmoniously. Nestled in a serene and picturesque location, each villa is meticulously designed to provide a perfect oasis for guests seeking relaxation and comfort. With spacious living areas..."
    val readMoreText = "Read more"

    val annotatedString = buildAnnotatedString {
        append(text)
        append(" ")
        pushStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline))
        append(readMoreText)
        pop()
        addStringAnnotation(tag = "ReadMore", annotation = "https://example.com", start = text.length + 1, end = text.length + 1 + readMoreText.length)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "ReadMore", start = offset, end = offset)
                    .firstOrNull()?.let {
                        // Handle read more click
                    }
            },
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
        )
    }
}

@Composable
fun HouseFacilities() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)) {
        Text("Our Facilities", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(4.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            // Left column
            Column(modifier = Modifier.weight(1f)) {
                FacilityItem(Icons.Default.PlayArrow, "Wi-Fi available")
                FacilityItem(Icons.Default.Star, "Hot water")
                FacilityItem(Icons.Default.CheckCircle, "Netflix, Spotify and etc.")
                FacilityItem(Icons.Default.Build, "AC - ductless split")
            }

            // Right column
            Column(modifier = Modifier.weight(1f)) {
                FacilityItem(Icons.Default.MoreVert, "Kitchen")
                FacilityItem(Icons.Default.Email, "4 bedrooms")
                FacilityItem(Icons.Default.Check, "Free parking lot")
                FacilityItem(Icons.Default.Add, "Many more facilities")
            }
        }
    }
}

@Composable
fun FacilityItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 14.sp)
    }
}

@Composable
fun LocationAddress() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text("Location", fontWeight = FontWeight.Bold, fontSize = 22.sp,style = MaterialTheme.typography.bodyLarge)
        // You would typically use a Map composable here
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        )
    }
}
@Composable
fun AgentDetails(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text("Advertiser", fontWeight = FontWeight.Bold, fontSize = 20.sp,style = MaterialTheme.typography.bodyMedium)
        Column(modifier = Modifier.border(width = 4.dp, color = Color.LightGray)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.lavington),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = { /* TODO: Handle call */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            ) {
                Icon(Icons.Outlined.Call, contentDescription = null, tint = Color.White)
                Text("Call Agent", fontSize = 16.sp, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = { /* Prompt to Chat */ },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
            ) {
                Icon(Icons.Outlined.MailOutline, contentDescription = null, tint = Color.White)
                Text("Message",fontSize = 16.sp, color = Color.White)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun VillaDetailPreview() {
    val navController = rememberNavController()
    HouseDetailsPage(navController = navController)
}
@Preview(showBackground = true)
@Composable
fun AgentDetailsPreview() {
    AgentDetails()

}
@Preview(showBackground = true)
@Composable
fun AgentDetailsPreview1() {
    LocationAddress()

}