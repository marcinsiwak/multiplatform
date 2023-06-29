package pl.msiwak.multiplatform.ui.welcome

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class WelcomeScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val navigator: Navigator,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeState())
    val viewState: StateFlow<WelcomeState> = _viewState

    private val errorHandler = globalErrorHandler.handleError()

    fun onLoginChanged(text: String) {
        _viewState.value = viewState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        _viewState.value = viewState.value.copy(password = text)
    }

    fun onLoginClicked() {
        viewModelScope.launch(errorHandler) {
            loginUseCase(LoginUseCase.Params(viewState.value.login, viewState.value.password))
            navigator.navigate(NavigationDirections.Dashboard)
        }
    }

    fun onGoogleLogin(idToken: String) {
        viewModelScope.launch {
            googleLoginUseCase(idToken)
            navigator.navigate(NavigationDirections.Dashboard)
        }
    }

    fun onRegistrationClicked() {
        navigator.navigate(NavigationDirections.Registration)
    }
}