import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import iz.housing.haofiti.R
import iz.housing.haofiti.viewmodels.AuthViewModel
import com.facebook.login.LoginManager



@Composable
fun AuthScreen(viewModel: AuthViewModel,navController: NavController) {
    val context = LocalContext.current as Activity

    val activityResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        viewModel.handleGoogleSignInResult(result.resultCode, result.data)
    }


    var isSignUpMode by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val authError by viewModel.authError

    val isAuthenticated by viewModel.isAuthenticated

    // Check if the user is authenticated, and navigate to the Home screen
    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            navController.navigate("home_screen") {
                popUpTo("auth_screen") { inclusive = true }
            }
        }
    }

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
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Sign In Box
                Box(
                    modifier = noRippleClickable(
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
                    contentAlignment = Alignment.Center
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
                    modifier = noRippleClickable(
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
                    contentAlignment = Alignment.Center
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

        if (authError != null) {
            Text(
                text = authError ?: "",
                color = Color.Red,
                style = MaterialTheme.typography.body2
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
                    IconButton(onClick = {
                        val googleSignInClient = viewModel.getGoogleSignInClient(context)
                        val signInIntent = googleSignInClient.signInIntent
                        activityResultLauncher.launch(signInIntent)
                    }) {
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
                Spacer(modifier = Modifier.width(16.dp))

                // Facebook Icon Button with Text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    IconButton(onClick = {
                        viewModel.setupFacebookLogin(context)
                        LoginManager.getInstance().logInWithReadPermissions(context, listOf("email", "public_profile"))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Facebook",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
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


@Composable
fun noRippleClickable(
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


