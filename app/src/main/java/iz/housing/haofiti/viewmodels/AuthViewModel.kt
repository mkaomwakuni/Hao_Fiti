package iz.housing.haofiti.viewmodels

import android.app.Activity
import android.content.Intent
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

    private lateinit var callbackManager: CallbackManager

    // Sign-in with email and password
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val authResult = authRepository.signIn(email, password)
            if (authResult == null) {
                authError.value = "Sign-in failed. Check your credentials."
            } else {
                authError.value = null
                isAuthenticated.value = true
            }
        }
    }

    // Sign-up with email, password, and confirm password
    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val authResult = authRepository.signUp(email, password, confirmPassword, name)
            if (authResult.errorMessage != null) {
                authError.value = authResult.errorMessage
            } else {
                authError.value = null
                isAuthenticated.value = true
            }
        }
    }

    // Sign-in with Google
    fun signInWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            try {
                val authResult = authRepository.firebaseAuthWithGoogle(account)
                if (authResult.errorMessage != null) {
                    authError.value = authResult.errorMessage
                } else {
                    authError.value = null
                    isAuthenticated.value = true
                }
            } catch (e: Exception) {
                authError.value = "Sign-in failed. An error occurred."
            }
        }
    }

    // Handle Google Sign-In result
    fun handleGoogleSignInResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                signInWithGoogle(it)
            }
        } else {
            authError.value = "Google Sign-In failed"
        }
    }

    fun getGoogleSignInClient(activity: Activity) = authRepository.getGoogleSignInClient(activity)

    // Setup Facebook login
    fun setupFacebookLogin(activity: Activity) {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                handleFacebookResult(result)
            }

            override fun onCancel() {
                authError.value = "Facebook login cancelled."
            }

            override fun onError(error: FacebookException) {
                authError.value = "Facebook login failed: ${error.message}"
            }
        })
    }

    // Handle Facebook LoginResult
    fun handleFacebookResult(result: LoginResult) {
        val token = result.accessToken
        handleFacebookAccessToken(token)
    }

    // Handle Facebook access token
    private fun handleFacebookAccessToken(token: AccessToken) {
        viewModelScope.launch {
            try {
                val authResult = authRepository.firebaseAuthWithFacebook(token)
                if (authResult != null) {
                    if (authResult.errorMessage != null) {
                        authError.value = authResult.errorMessage
                    } else {
                        authError.value = null
                        isAuthenticated.value = true
                    }
                }
            } catch (e: Exception) {
                authError.value = "Authentication failed"
            }
        }
    }

    fun getFacebookCallbackManager(): CallbackManager {
        return callbackManager
    }
}
