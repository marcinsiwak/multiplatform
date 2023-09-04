package pl.msiwak.multiplatform.core.ui.register

import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.MR
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.core.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.core.data.common.PasswordRequirement
import pl.msiwak.multiplatform.core.data.common.PasswordRequirementType
import pl.msiwak.multiplatform.core.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.core.ui.navigator.Navigator
import pl.msiwak.multiplatform.core.validators.Validator

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validator: Validator,
    globalErrorHandler: GlobalErrorHandler,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(RegisterState())
    val viewState: StateFlow<RegisterState> = _viewState.asStateFlow()

    private val errorHandler = globalErrorHandler.handleError { throwable ->
        when(throwable) {
            is FirebaseAuthUserCollisionException -> {
                navigator.navigate(NavigationDirections.VerifyEmail)
                true
            }
            else -> false
        }
    }

    fun onLoginChanged(text: String) {
        _viewState.update { it.copy(login = text, loginErrorMessage = null) }
    }

    fun onPasswordChanged(text: String) {
        _viewState.update {
            it.copy(
                password = text,
                passwordErrorMessage = null,
                passwordRequirements = getPasswordRequirementsState(text)
            )
        }
    }

    private fun getPasswordRequirementsState(text: String): List<PasswordRequirement> {
        return viewState.value.passwordRequirements.map {

            when (it.type) {
                PasswordRequirementType.LENGTH -> it.copy(
                    isCorrect = validator.isCorrectPasswordLength(
                        text
                    )
                )

                PasswordRequirementType.NUMBER -> it.copy(
                    isCorrect = validator.hasPasswordNumber(
                        text
                    )
                )

                PasswordRequirementType.LETTER -> it.copy(
                    isCorrect = validator.hasPasswordLetter(
                        text
                    )
                )

                PasswordRequirementType.CAPITAL -> it.copy(
                    isCorrect = validator.hasPasswordCapitalLetter(
                        text
                    )
                )

                PasswordRequirementType.SPECIAL -> it.copy(
                    isCorrect = validator.hasPasswordSpecialCharacter(
                        text
                    )
                )
            }
        }
    }

    fun onRegisterClicked() {
        val isPasswordValid = validator.validatePassword(viewState.value.password)
        val isLoginValid = validator.validateEmail(viewState.value.login)

        if (isPasswordValid) {
            _viewState.update { it.copy(passwordErrorMessage = null) }
        } else {
            _viewState.update { it.copy(passwordErrorMessage = MR.strings.input_wrong_format) }
        }
        if (isLoginValid) {
            _viewState.update { it.copy(loginErrorMessage = null) }
        } else {
            _viewState.update { it.copy(loginErrorMessage = MR.strings.input_wrong_format) }
        }

        if (!isPasswordValid || !isLoginValid) return

        viewModelScope.launch(errorHandler) {
            registerUserUseCase(
                RegisterUserUseCase.Params(
                    viewState.value.login,
                    viewState.value.password
                )
            )
            navigator.navigate(NavigationDirections.VerifyEmail)
        }
    }

    fun onVisibilityClicked() {
        _viewState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }
}