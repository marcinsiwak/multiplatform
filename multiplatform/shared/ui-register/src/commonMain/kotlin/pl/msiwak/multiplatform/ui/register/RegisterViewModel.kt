package pl.msiwak.multiplatform.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.input_wrong_format
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.PasswordRequirement
import pl.msiwak.multiplatform.commonObject.PasswordRequirementType
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.validators.Validator

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validator: Validator,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(RegisterState())
    val viewState: StateFlow<RegisterState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<RegisterEvent>()
    val viewEvent: SharedFlow<RegisterEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError { throwable ->
        when (throwable) {
            is FirebaseAuthUserCollisionException -> {
                viewModelScope.launch {
                    _viewEvent.emit(RegisterEvent.NavigateToVerifyEmail)
                }
                true
            }

            else -> false
        }
    }

    fun onUiAction(action: RegisterUiAction) {
        when (action) {
            is RegisterUiAction.OnLoginChanged -> onLoginChanged(action.login)
            is RegisterUiAction.OnPasswordChanged -> onPasswordChanged(action.password)
            RegisterUiAction.OnRegisterClicked -> onRegisterClicked()
            is RegisterUiAction.OnTermsClicked -> onTermsClicked(action.isChecked)
            RegisterUiAction.OnVisibilityClicked -> onVisibilityClicked()
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
                PasswordRequirementType.LENGTH -> it.copy(isCorrect = validator.isCorrectPasswordLength(text))
                PasswordRequirementType.NUMBER -> it.copy(isCorrect = validator.hasPasswordNumber(text))
                PasswordRequirementType.LETTER -> it.copy(isCorrect = validator.hasPasswordLetter(text))
                PasswordRequirementType.CAPITAL -> it.copy(isCorrect = validator.hasPasswordCapitalLetter(text))
                PasswordRequirementType.SPECIAL -> it.copy(isCorrect = validator.hasPasswordSpecialCharacter(text))
            }
        }
    }

    fun onRegisterClicked() {
        val isPasswordValid = validator.validatePassword(viewState.value.password)
        val isLoginValid = validator.validateEmail(viewState.value.login)

        if (isPasswordValid) {
            _viewState.update { it.copy(passwordErrorMessage = null) }
        } else {
            _viewState.update { it.copy(passwordErrorMessage = Res.string.input_wrong_format) }
        }
        if (isLoginValid) {
            _viewState.update { it.copy(loginErrorMessage = null) }
        } else {
            _viewState.update { it.copy(loginErrorMessage = Res.string.input_wrong_format) }
        }

        if (!isPasswordValid || !isLoginValid) return

        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            registerUserUseCase(
                RegisterUserUseCase.Params(
                    viewState.value.login,
                    viewState.value.password
                )
            )
            _viewState.update { it.copy(isLoading = false) }
            _viewEvent.emit(RegisterEvent.NavigateToVerifyEmail)
        }
    }

    fun onVisibilityClicked() {
        _viewState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onTermsClicked(isChecked: Boolean) {
        _viewState.update { it.copy(isTermsChecked = isChecked) }
    }
}
