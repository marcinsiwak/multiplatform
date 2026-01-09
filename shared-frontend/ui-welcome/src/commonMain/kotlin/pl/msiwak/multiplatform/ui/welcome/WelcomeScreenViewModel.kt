package pl.msiwak.multiplatform.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.AuthProvider
import pl.msiwak.multiplatform.commonObject.exception.ClientErrorException
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginWithProviderUseCase
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class WelcomeScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val setOfflineModeUseCase: SetOfflineModeUseCase,
    private val loginWithProviderUseCase: LoginWithProviderUseCase,
    private val getUser: GetUserUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeState())
    val viewState: StateFlow<WelcomeState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<WelcomeEvent>()
    val viewEvent: SharedFlow<WelcomeEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError { throwable ->
        when {
            throwable is ClientErrorException && throwable.httpCode == 404 -> {
                viewModelScope.launch {
                    _viewEvent.emit(WelcomeEvent.NavigateToTermsAndConditions)
                }
                true
            }

            else -> {
                _viewState.update { it.copy(isErrorDialogVisible = true, isLoading = false) }
                false
            }
        }
    }

    fun onUiAction(action: WelcomeUiAction) {
        when (action) {
            WelcomeUiAction.OnConfirmDialogButtonClicked -> onConfirmDialogButtonClicked()
            is WelcomeUiAction.OnLoginChanged -> onLoginChanged(action.login)
            WelcomeUiAction.OnLoginClicked -> onLoginClicked()
            WelcomeUiAction.OnOfflineModeClicked -> onOfflineModeClicked()
            is WelcomeUiAction.OnPasswordChanged -> onPasswordChanged(action.password)
            WelcomeUiAction.OnRegistrationClicked -> onRegistrationClicked()
            WelcomeUiAction.OnVisibilityClicked -> onVisibilityClicked()
            is WelcomeUiAction.OnGoogleLoginSucceed -> onGoogleLoginSucceed(
                action.idToken,
                action.accessToken
            )

            is WelcomeUiAction.OnAppleLoginSucceed -> onAppleLoginSucceed(
                action.tokenString,
                action.nonce,
                action.error
            )

            else -> Unit
        }
    }

    private fun onGoogleLoginSucceed(idToken: String, accessToken: String?) {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            loginWithProviderUseCase(AuthProvider.Google(idToken, accessToken))
            getUser()
            _viewState.update { it.copy(isLoading = false) }
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }

    private fun onAppleLoginSucceed(tokenString: String?, nonce: String?, error: String?) {
        viewModelScope.launch(errorHandler) {
            if (error != null) throw Exception(error)
            _viewState.update { it.copy(isLoading = true) }
            if (tokenString == null || nonce == null) throw Exception("Failed to fetch identity token")
            loginWithProviderUseCase(AuthProvider.Apple(tokenString, nonce))
            getUser()
            _viewState.update { it.copy(isLoading = false) }
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }

    private fun onLoginChanged(text: String) {
        _viewState.update { it.copy(login = text, authErrorMessage = null) }
    }

    private fun onPasswordChanged(text: String) {
        _viewState.update { it.copy(password = text, authErrorMessage = null) }
    }

    private fun onLoginClicked() {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            val isUserVerified = loginUseCase(LoginUseCase.Params(viewState.value.login, viewState.value.password))
            if (isUserVerified) {
                getUser()
                _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
            } else {
                _viewEvent.emit(WelcomeEvent.NavigateToVerifyEmail)
            }
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    private fun onOfflineModeClicked() {
        // todo: improve offline mode - currently feature is disabled
        viewModelScope.launch {
            setOfflineModeUseCase(true)
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }

    private fun onRegistrationClicked() {
        viewModelScope.launch {
            _viewEvent.emit(WelcomeEvent.NavigateToRegistration)
        }
    }

    private fun onVisibilityClicked() {
        _viewState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun onConfirmDialogButtonClicked() {
        _viewState.update { it.copy(isErrorDialogVisible = false) }
    }
}
