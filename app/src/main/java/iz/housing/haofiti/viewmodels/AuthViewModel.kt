package iz.housing.haofiti.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iz.housing.haofiti.data.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    var isSignUpMode = mutableStateOf(false)
        private set

    var authError = mutableStateOf<String?>(null)


    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val user = authRepository.signIn(email, password)
            if (user == null) {
                authError.value = "Sign-in failed. Check your credentials."
            }
        }
    }

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val user = authRepository.signUp(email, password, confirmPassword, name)
            if (user == null) {
                authError.value = "Sign-up failed. Check your details."
            }
        }
    }
}

