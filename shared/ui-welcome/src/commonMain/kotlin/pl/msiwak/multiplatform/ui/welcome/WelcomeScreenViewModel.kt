package pl.msiwak.multiplatform.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCase
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class WelcomeScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val setOfflineModeUseCase: SetOfflineModeUseCase,
    private val checkIfSynchronizationIsPossibleUseCase: CheckIfSynchronizationIsPossibleUseCase,
    private val synchronizeDatabaseUseCase: SynchronizeDatabaseUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeState())
    val viewState: StateFlow<WelcomeState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<WelcomeEvent>()
    val viewEvent: SharedFlow<WelcomeEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError { throwable ->
        when (throwable) {
            is FirebaseAuthInvalidCredentialsException -> {
                _viewState.update {
                    it.copy(authErrorMessage = "", isErrorDialogVisible = true)
                }
                true
            }

            else -> false
        }
    }

    fun onLoginChanged(text: String) {
        _viewState.update { it.copy(login = text, authErrorMessage = null) }
    }

    fun onPasswordChanged(text: String) {
        _viewState.update { it.copy(password = text, authErrorMessage = null) }
    }

    fun onLoginClicked() {
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

    fun onOfflineModeClicked() {
        viewModelScope.launch {
            setOfflineModeUseCase(true)
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }

    fun onRegistrationClicked() {
        viewModelScope.launch {
            _viewEvent.emit(WelcomeEvent.NavigateToRegistration)
        }
    }

    fun onVisibilityClicked() {
        _viewState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onConfirmDialogButtonClicked() {
        _viewState.update { it.copy(isErrorDialogVisible = false) }
    }

    fun onConfirmSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            synchronizeDatabaseUseCase()
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onDismissSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch {
            _viewEvent.emit(WelcomeEvent.NavigateToDashboard)
        }
    }
}
