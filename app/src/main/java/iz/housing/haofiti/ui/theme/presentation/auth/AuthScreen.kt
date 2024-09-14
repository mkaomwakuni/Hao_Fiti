import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iz.housing.haofiti.R
import iz.housing.haofiti.viewmodels.AuthViewModel

@Composable
fun NoRippleClickable(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
): Modifier {
    return modifier
        .clickable(
            onClick = onClick,
            indication = null, // Disable ripple effect
            interactionSource = remember { MutableInteractionSource() }
        )
}

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
    var isSignUpMode by remember { mutableStateOf(false) } // Toggle state for sign in/sign up

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome",
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Slider-style buttons for Sign In / Sign Up
        Box(
            modifier = Modifier
                .background(Color.LightGray, CircleShape)
                .padding(4.dp)
                .fillMaxWidth(0.6f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Sign In Box
                Box(
                    modifier = NoRippleClickable(
                        onClick = { isSignUpMode = false },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(
                                if (!isSignUpMode) Color.White else Color.Transparent,
                                CircleShape
                            )
                            .padding(8.dp)
                    ),
                    contentAlignment = Alignment.Center // Fixed alignment inside the Box
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 16.sp,
                        fontWeight = if (!isSignUpMode) FontWeight.Bold else FontWeight.Normal,
                        color = if (!isSignUpMode) MaterialTheme.colors.primary else Color.Gray
                    )
                }

                // Sign Up Box
                Box(
                    modifier = NoRippleClickable(
                        onClick = { isSignUpMode = true },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(
                                if (isSignUpMode) Color.White else Color.Transparent,
                                CircleShape
                            )
                            .padding(8.dp)
                    ),
                    contentAlignment = Alignment.Center // Fixed alignment inside the Box
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp,
                        fontWeight = if (isSignUpMode) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSignUpMode) MaterialTheme.colors.primary else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isSignUpMode) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("UserName", fontSize = 16.sp) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        if (isSignUpMode) {
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isSignUpMode) {
                    viewModel.signUp(name, email, password, confirmPassword)
                } else {
                    viewModel.signIn(email, password)
                }
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, Color.Gray, CircleShape)
        ) {
            Text(text = "Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Or Continue with")
            Spacer(modifier = Modifier.width(8.dp))
            Divider(modifier = Modifier.weight(2f))
        }

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                // Google Icon Button with Text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    IconButton(onClick = { /* Handle Google login */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Google",
                        style = MaterialTheme.typography.button
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Or",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Space after "Or" text

                // Facebook Icon Button with Text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    IconButton(onClick = { /* Handle Facebook login */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Facebook",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp)) // Space between icon and text
                    Text(
                        text = "Facebook",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isSignUpMode) "Already have an account? " else "Don't have an account? ",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = if (isSignUpMode) "Sign in" else "Sign up",
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary),
                modifier = Modifier.clickable {
                    isSignUpMode = !isSignUpMode
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AuthScreenPreview() {
//    val viewModel = AuthViewModel()
//    AuthScreen(viewModel = viewModel)
//}
