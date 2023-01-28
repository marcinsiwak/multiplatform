package pl.msiwak.multiplatform.ui.register

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.validators.Validator

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validator: Validator,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    val errorHandler = globalErrorHandler.handleError()

    fun onLoginChanged(text: String) {
        val isLoginValid = validator.validateEmail(text)
        if (isLoginValid) {
            _registerState.value = registerState.value.copy(loginErrorMessage = null)
        } else {
            _registerState.value = registerState.value.copy(loginErrorMessage = "wrong format")
        }
        _registerState.value = registerState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        val isPasswordValid = validator.validatePassword(text)
        if (isPasswordValid) {
            _registerState.value = registerState.value.copy(passwordErrorMessage = null)
        } else {
            _registerState.value = registerState.value.copy(passwordErrorMessage = "wrong format")
        }
        _registerState.value = registerState.value.copy(password = text)
    }

    fun onRegisterClicked() {
        viewModelScope.launch(errorHandler) {
            registerUserUseCase(
                RegisterUserUseCase.Params(
                    registerState.value.login,
                    registerState.value.password
                )
            )
        }
    }

}