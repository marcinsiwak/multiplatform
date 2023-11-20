package pl.msiwak.multiplatform.ui.welcome

import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCase
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class WelcomeScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val navigator: Navigator,
    private val setOfflineModeUseCase: SetOfflineModeUseCase,
    private val checkIfSynchronizationIsPossibleUseCase: CheckIfSynchronizationIsPossibleUseCase,
    private val synchronizeDatabaseUseCase: SynchronizeDatabaseUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(WelcomeState())
    val viewState: StateFlow<WelcomeState> = _viewState.asStateFlow()

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
                    navigator.navigate(NavigationDirections.Dashboard(true))
                }
            } else {
                navigator.navigate(NavigationDirections.VerifyEmail)
            }
        }
    }

    fun onGoogleLogin(idToken: String?, accessToken: String?) {
        viewModelScope.launch {
            val isSynchronizationPossible = checkIfSynchronizationIsPossibleUseCase()
            _viewState.update { it.copy(isLoading = true) }
            googleLoginUseCase(idToken, accessToken)
            if (isSynchronizationPossible) {
                _viewState.update { it.copy(isSynchronizationDialogVisible = true) }
            } else {
                navigator.navigate(NavigationDirections.Dashboard(true))
            }
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    // TODO: Add Apple login

    fun onOfflineModeClicked() {
        viewModelScope.launch {
            setOfflineModeUseCase(true)
            navigator.navigate(NavigationDirections.Dashboard(true))
        }
    }

    fun onRegistrationClicked() {
        navigator.navigate(NavigationDirections.Registration)
    }

    fun onVisibilityClicked() {
        _viewState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onConfirmDialogButtonClicked() {
        _viewState.update { it.copy(isErrorDialogVisible = false) }
    }

    fun onConfirmSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }
            synchronizeDatabaseUseCase()
            navigator.navigate(NavigationDirections.Dashboard(true))
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onDismissSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
    }
}