package pl.msiwak.multiplatform.ui.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    val errorHandler = globalErrorHandler.handleError()

    fun onLoginChanged(text: String) {
        _loginState.value = loginState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        _loginState.value = loginState.value.copy(password = text)
    }

    fun onLoginClicked() {
        viewModelScope.launch(errorHandler) {
            loginUseCase(LoginUseCase.Params(loginState.value.login, loginState.value.password))
            navigator.navigate(NavigationDirections.Dashboard)
        }
    }
}