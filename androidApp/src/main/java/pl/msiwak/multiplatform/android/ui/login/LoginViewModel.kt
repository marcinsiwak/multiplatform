package pl.msiwak.multiplatform.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.authorization.FirebaseAuthorization

class LoginViewModel(private val firebaseAuthorization: FirebaseAuthorization) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun onLoginChanged(text: String) {
        _loginState.value = loginState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        _loginState.value = loginState.value.copy(password = text)
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            val result = firebaseAuthorization.loginUser(
                loginState.value.login,
                loginState.value.password
            )
        }
    }
}