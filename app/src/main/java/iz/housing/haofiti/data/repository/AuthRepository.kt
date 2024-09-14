package iz.housing.haofiti.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Sign Up method
    suspend fun signUp(email: String, password: String, confirmPassword: String, username: String): FirebaseUser? {
        return if (password == confirmPassword) {
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val user = result.user

                user?.let {
                    // Store user name in firestore
                    val userProfile = mapOf(
                        "username" to username,
                        "email" to email
                    )
                    firestore.collection("users").document(user.uid).set(userProfile).await()
                }

                user
            } catch (e: Exception) {
                null
            }
        } else {
            null
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
}

