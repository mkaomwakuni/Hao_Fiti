package com.example.haofiti.presentation.onboarding

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .height(393.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(bottomStart = 60.dp))
                    .background(color = Color.Transparent)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id =  R.drawable.kino),
                    contentDescription = "banner"  )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Texts(textResId = R.string.splash_screen_title)

            Spacer(modifier = Modifier.height(5.dp))

            TextSubs(textResId = R.string.splash_screen_subtitle)

            Spacer(modifier = Modifier.height(10.dp))

            TextButtonSplash(textResId = R.string.get_started_button, onClick = {})
        }
    }
}


@Composable
fun Texts(
    @StringRes textResId: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = FontWeight.Bold,
    fontFamily: FontFamily? = FontFamily.Default,
    textAlign: TextAlign? = TextAlign.Start
) {
    Text(
        modifier = modifier.padding(20.dp),
        text = stringResource(id = textResId),
        fontSize = 50.sp,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        textAlign = textAlign
    )
}

@Composable
fun TextSubs(
    @StringRes textResId: Int,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily? = FontFamily.Cursive,
    fontWeight: FontWeight? = FontWeight.Normal,
    textAlign: TextAlign? = TextAlign.Start
) {
    Text(
        modifier = modifier.padding(20.dp),
        text = stringResource(id = textResId),
        fontSize = 20.sp,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        textAlign = textAlign
    )
}

@Composable
fun TextButtonSplash(
    @StringRes textResId: Int,
    onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.Gray, shape = RoundedCornerShape(20.dp))
    ) {
        Text(
            text = stringResource(id = textResId),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

