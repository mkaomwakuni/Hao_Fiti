package iz.housing.haofiti.viewmodels

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    var isAuthenticated = mutableStateOf(false)
        private set

    var authError = mutableStateOf<String?>(null)

    private var onAuthenticationSuccess: (() -> Unit)? = null
    private lateinit var callbackManager: CallbackManager

    fun setOnAuthenticationSuccess(callback: () -> Unit) {
        onAuthenticationSuccess = callback
    }

    // Sign-in with email and password
    fun signIn(email: String, password: String) {
        Log.d("AuthViewModel", "Attempting to sign in with email: $email")
        viewModelScope.launch {
            val authResult = authRepository.signIn(email, password)
            if (authResult == null) {
                authError.value = "Sign-in failed. Check your credentials."
                Log.e("AuthViewModel", "Sign-in failed for email: $email")
            } else {
                authError.value = null
                isAuthenticated.value = true
                Log.d("AuthViewModel", "Sign-in successful for email: $email")
                onAuthenticationSuccess?.invoke()
            }
        }
    }

    // Sign-up with email, password, and confirm password
    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        Log.d("AuthViewModel", "Attempting to sign up with email: $email")
        viewModelScope.launch {
            val authResult = authRepository.signUp(email, password, confirmPassword, name)
            if (authResult.errorMessage != null) {
                authError.value = authResult.errorMessage
                Log.e("AuthViewModel", "Sign-up failed: ${authResult.errorMessage}")
            } else {
                authError.value = null
                isAuthenticated.value = true
                Log.d("AuthViewModel", "Sign-up successful for email: $email")
            }
        }
    }

    // Sign-in with Google
    fun signInWithGoogle(account: GoogleSignInAccount) {
        Log.d("AuthViewModel", "Attempting to sign in with Google account: ${account.email}")
        viewModelScope.launch {
            try {
                val authResult = authRepository.firebaseAuthWithGoogle(account)
                if (authResult.errorMessage != null) {
                    authError.value = authResult.errorMessage
                    Log.e("AuthViewModel", "Google sign-in failed: ${authResult.errorMessage}")
                } else {
                    authError.value = null
                    isAuthenticated.value = true
                    Log.d("AuthViewModel", "Google sign-in successful for account: ${account.email}")
                }
            } catch (e: Exception) {
                authError.value = "Sign-in failed. An error occurred."
                Log.e("AuthViewModel", "Google sign-in error: ${e.message}")
            }
        }
    }

    // Handle Google Sign-In result
    fun handleGoogleSignInResult(resultCode: Int, data: Intent?) {
        Log.d("AuthViewModel", "Handling Google sign-in result with resultCode: $resultCode")
        if (resultCode == Activity.RESULT_OK) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                signInWithGoogle(it)
            }
        } else {
            authError.value = "Google Sign-In failed"
            Log.e("AuthViewModel", "Google Sign-In failed with resultCode: $resultCode")
        }
    }

    fun getGoogleSignInClient(activity: Activity) = authRepository.getGoogleSignInClient(activity)

    // Setup Facebook login
    fun setupFacebookLogin(activity: Activity) {
        Log.d("AuthViewModel", "Setting up Facebook login")
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d("AuthViewModel", "Facebook login successful")
                handleFacebookResult(result)
            }

            override fun onCancel() {
                authError.value = "Facebook login cancelled."
                Log.w("AuthViewModel", "Facebook login cancelled by user")
            }

            override fun onError(error: FacebookException) {
                authError.value = "Facebook login failed: ${error.message}"
                Log.e("AuthViewModel", "Facebook login error: ${error.message}")
            }
        })
    }

    // Handle Facebook LoginResult
    fun handleFacebookResult(result: LoginResult) {
        Log.d("AuthViewModel", "Handling Facebook login result")
        val token = result.accessToken
        handleFacebookAccessToken(token)
    }

    // Handle Facebook access token
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("AuthViewModel", "Handling Facebook access token: ${token.token}")
        viewModelScope.launch {
            try {
                val authResult = authRepository.firebaseAuthWithFacebook(token)
                if (authResult != null) {
                    if (authResult.errorMessage != null) {
                        authError.value = authResult.errorMessage
                        Log.e("AuthViewModel", "Facebook authentication failed: ${authResult.errorMessage}")
                    } else {
                        authError.value = null
                        isAuthenticated.value = true
                        Log.d("AuthViewModel", "Facebook authentication successful")
                    }
                }
            } catch (e: Exception) {
                authError.value = "Authentication failed"
                Log.e("AuthViewModel", "Error during Facebook authentication: ${e.message}")
            }
        }
    }

    fun getFacebookCallbackManager(): CallbackManager {
        return callbackManager
    }
}
