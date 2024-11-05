package pl.msiwak.multiplatform.ui.welcome.terms

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
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class TermsConfirmationViewModel(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val checkIfSynchronizationIsPossibleUseCase: CheckIfSynchronizationIsPossibleUseCase,
    private val synchronizeDatabaseUseCase: SynchronizeDatabaseUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(TermsConfirmationState())
    val viewState: StateFlow<TermsConfirmationState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<TermsConfirmationEvent>()
    val viewEvent: SharedFlow<TermsConfirmationEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError()

    fun onUiAction(action: TermsConfirmationUiAction) {
        when (action) {
            is TermsConfirmationUiAction.OnButtonClick -> onGoogleLogin(
                action.idToken,
                action.accessToken
            )

            TermsConfirmationUiAction.OnConfirmSynchronizationClicked -> onConfirmSynchronizationClicked()
            TermsConfirmationUiAction.OnDismissSynchronizationClicked -> onDismissSynchronizationClicked()
            else -> Unit
        }
    }

    private fun onGoogleLogin(idToken: String?, accessToken: String?) {
        viewModelScope.launch(errorHandler) {
            val isSynchronizationPossible = checkIfSynchronizationIsPossibleUseCase()
            _viewState.update { it.copy(isLoading = true) }
            googleLoginUseCase(idToken, accessToken)
            if (isSynchronizationPossible) {
                _viewState.update { it.copy(isSynchronizationDialogVisible = true) }
            } else {
                _viewEvent.emit(TermsConfirmationEvent.NavigateToDashboard)
            }
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    private fun onConfirmSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            synchronizeDatabaseUseCase()
            _viewEvent.emit(TermsConfirmationEvent.NavigateToDashboard)
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    private fun onDismissSynchronizationClicked() {
        _viewState.update { it.copy(isSynchronizationDialogVisible = false) }
        viewModelScope.launch {
            _viewEvent.emit(TermsConfirmationEvent.NavigateToDashboard)
        }
    }
}
