package pl.msiwak.multiplatform.ui.register

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.validators.Validator

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validator: Validator,
    globalErrorHandler: GlobalErrorHandler,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(RegisterState())
    val viewState: StateFlow<RegisterState> = _viewState

    val errorHandler = globalErrorHandler.handleError()

    fun onLoginChanged(text: String) {
        val isLoginValid = validator.validateEmail(text)
        if (isLoginValid) {
            _viewState.update {  it.copy(loginErrorMessage = null) }
        } else {
            _viewState.update { it.copy(loginErrorMessage = "wrong format") }
        }
        _viewState.update { it.copy(login = text) }
    }

    fun onPasswordChanged(text: String) {
        val isPasswordValid = validator.validatePassword(text)
        if (isPasswordValid) {
            _viewState.update { it.copy(passwordErrorMessage = null) }
        } else {
            _viewState.update {  it.copy(passwordErrorMessage = "wrong format") }
        }
        _viewState.update { it.copy(password = text) }
    }

    fun onRegisterClicked() {
        viewModelScope.launch(errorHandler) {
            registerUserUseCase(
                RegisterUserUseCase.Params(
                    viewState.value.login,
                    viewState.value.password
                )
            )
            navigator.navigate(NavigationDirections.Dashboard)
        }
    }

}