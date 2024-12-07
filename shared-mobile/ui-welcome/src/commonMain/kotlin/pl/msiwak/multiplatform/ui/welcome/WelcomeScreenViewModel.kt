package pl.msiwak.multiplatform.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCase
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class WelcomeScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val setOfflineModeUseCase: SetOfflineModeUseCase,
    private val checkIfSynchronizationIsPossibleUseCase: CheckIfSynchronizationIsPossibleUseCase,
    private val synchronizeDatabaseUseCase: SynchronizeDatabaseUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val getUser: GetUserUseCase,
    private val globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeState())
    val viewState: StateFlow<WelcomeState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<WelcomeEvent>()
    val viewEvent: SharedFlow<WelcomeEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError { throwable ->
        when (throwable) {
//            is FirebaseAuthInvalidCredentialsException -> {
//                _viewState.update {
//                    it.copy(authErrorMessage = "", isErrorDialogVisible = true)
//                }
//                true
//            }

            else -> false
        }
    }

    private fun prepareGoogleLoginErrorHandler(uuid: String): CoroutineExceptionHandler =
        globalErrorHandler.handleError { _ ->
            viewModelScope.launch(errorHandler) {
                _viewEvent.emit(WelcomeEvent.NavigateToTermsAndConditions(uuid))
            }
            false
        }

    fun onUiAction(action: WelcomeUiAction) {
        when (action) {
            WelcomeUiAction.OnConfirmDialogButtonClicked -> onConfirmDialogButtonClicked()
            WelcomeUiAction.OnConfirmSynchronizationClicked -> onConfirmSynchronizationClicked()
            WelcomeUiAction.OnDismissSynchronizationClicked -> onDismissSynchronizationClicked()
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

            else -> Unit
        }
    }

    private fun onGoogleLoginSucceed(idToken: String, accessToken: String?) {
        viewModelScope.launch(errorHandler) {

            val loginJob = async {
                val result = googleLoginUseCase(idToken, accessToken)
                return@async result
            }
            val uuid = loginJob.await() ?: throw Exception()

            viewModelScope.launch(prepareGoogleLoginErrorHandler(uuid)) {
                getUser()
                _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
            }
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
            val isUserVerified =
                loginUseCase(LoginUseCase.Params(viewState.value.login, viewState.value.password))
            _viewState.update { it.copy(isLoading = false) }
            val isSynchronizationPossible = checkIfSynchronizationIsPossibleUseCase()

            if (isUserVerified) {
                if (isSynchronizationPossible) {
                    _viewState.update { it.copy(isSynchronizationDialogVisible = true) }
                } else {
                    _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
                }
            } else {
                _viewEvent.emit(WelcomeEvent.NavigateToVerifyEmail)
            }
        }
    }

    // TODO: Add Apple login

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

    private fun onConfirmSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            synchronizeDatabaseUseCase()
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    private fun onDismissSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch {
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }
}
