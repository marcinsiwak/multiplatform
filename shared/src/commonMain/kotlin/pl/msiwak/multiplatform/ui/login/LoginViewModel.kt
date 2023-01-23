package pl.msiwak.multiplatform.ui.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

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
            loginUseCase(
                LoginUseCase.Params(
                    loginState.value.login,
                    loginState.value.password
                )
            )
        }
    }
}