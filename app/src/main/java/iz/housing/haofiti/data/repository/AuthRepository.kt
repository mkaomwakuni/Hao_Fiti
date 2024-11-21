package iz.housing.haofiti.data.repository

import android.app.Activity
import android.content.Context
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import iz.housing.haofiti.R
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val context: Context) {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    data class AuthResult(val user: FirebaseUser?, val errorMessage: String? = null)

    // Sign Up method
    suspend fun signUp(email: String, password: String, confirmPassword: String, username: String): AuthResult {
        return if (password == confirmPassword) {
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val user = result.user

                user?.let {
                    val userProfile = mapOf("username" to username, "email" to email)
                    firestore.collection("users").document(user.uid).set(userProfile).await()
                }

                AuthResult(user = user)
            } catch (e: Exception) {
                AuthResult(user = null, errorMessage = e.localizedMessage)
            }
        } else {
            AuthResult(user = null, errorMessage = "Passwords do not match")
        }
    }

    // Sign In method
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    // Get the current signed-in user
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Sign out the current user
    fun signOut() {
        firebaseAuth.signOut()
    }

    // Prepare Google Sign-In Client
    fun getGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    // Get GoogleSignInClient for sign-in process
    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        val googleSignInOptions = getGoogleSignInOptions()
        return com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(activity, googleSignInOptions)
    }

    // Handle Google Sign-In result
    suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount): AuthResult {
        return try {
            val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()

            val user = result.user
            AuthResult(user = user)
        } catch (e: Exception) {
            AuthResult(user = null, errorMessage = e.localizedMessage)
        }
    }

    //facebook login
    suspend fun firebaseAuthWithFacebook(token: AccessToken): AuthResult? {
        val credential = FacebookAuthProvider.getCredential(token.token)
        return try {
            val result = firebaseAuth.signInWithCredential(credential).await()
            val user = result.user
            AuthResult(user = user)
        } catch (e: Exception) {
            AuthResult(null, e.message)
        }
    }

}
