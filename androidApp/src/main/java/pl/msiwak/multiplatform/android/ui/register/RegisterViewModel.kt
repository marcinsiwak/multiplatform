package pl.msiwak.multiplatform.android.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.authorization.FirebaseAuthorization

class RegisterViewModel(private val firebaseAuthorization: FirebaseAuthorization) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    fun onLoginChanged(text: String) {
        _registerState.value = registerState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        _registerState.value = registerState.value.copy(password = text)
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            firebaseAuthorization.createNewUser(
                registerState.value.login,
                registerState.value.password
            )
        }
    }

}