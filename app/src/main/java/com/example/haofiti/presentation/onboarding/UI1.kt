package com.example.login.ui.theme

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.haofiti.R

@Composable
fun LoginScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2FAB6B), Color(0xFF12663F))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Welcome",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Let's explore the natural benefits around us that can serve as natural herbal medicine...\n",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        Column (
            modifier = Modifier
                .fillMaxSize()) {
            BottomSheetLayout(
                backgroundColor = Color.White,
                sheetHeight = 600.dp,
                sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Sign In",
                        fontSize = 28.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    TextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        placeholder = { Text("Username") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                    )
                    TextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
                        placeholder = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Forgot Password",
                        fontSize = 14.sp,
                        color = Color.Green,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Don't have an account?",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "  Sign Up",
                            fontSize = 14.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }

                    TextButtonUi(
                        text = "Sign In",
                        isSelected = false) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)) {
                        Divider(modifier = Modifier.width(170.dp), color = Color.LightGray, thickness = 2.dp)
                        Text(text = "OR")
                        Divider(modifier = Modifier.width(180.dp), color = Color.LightGray, thickness = 2.dp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    GoogleSignInButton(onClick = {})

                }
            }
        }
    }
}

@Composable
fun BottomSheetLayout(
    backgroundColor: Color,
    sheetHeight: Dp,
    sheetShape: Shape,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(sheetHeight)
            .background(color = backgroundColor, shape = sheetShape),
        content = content
    )
}

@Composable
fun TextButtonUi(
    text: String,
    isSelected:Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background( Color(0xFF2FAB6B)), shape = RoundedCornerShape(10.dp))

    {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp)
            .shadow(4.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background( Color.White), shape = RoundedCornerShape(10.dp))
    {
        Icon(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google Sign")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Continue With Google",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
