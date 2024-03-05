package com.example.haofiti.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haofiti.R

// SplashScreen.kt
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(335.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(bottomStart = 60.dp))
                    .background(color = Color.White)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.kino),
                    contentDescription = "banner"
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Texts(text = "Find Your\nIdeal Home")

            Spacer(modifier = Modifier.height(5.dp))

            TextSubs(text = "Just within clicks you can search\nand find your Dream Home and stay")

            Spacer(modifier = Modifier.height(60.dp))

            TextButtonSplash(text = "Get Started", onClick = {})
        }
    }
}


@Composable
fun Texts(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight? = FontWeight.Bold,
    textAlign: TextAlign? = TextAlign.Start
) {
    Text(
        modifier = modifier
            .padding(20.dp),
        text = text,
        fontSize = 40.sp,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}
@Composable
fun TextSubs(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontWeight: FontWeight? = FontWeight.Normal,
    textAlign: TextAlign? = TextAlign.Start
) {
    Text(
        modifier = modifier
            .padding(20.dp),
        text = text,
        fontSize = 20.sp,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}
@Composable
fun TextButtonSplash(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
                .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFF2FAB6B), shape = RoundedCornerShape(10.dp))


    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Preview()
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

