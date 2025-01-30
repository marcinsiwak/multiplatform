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
import pl.msiwak.multiplatform.domain.user.CreateUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class TermsConfirmationViewModel(
    private val createUserUseCase: CreateUserUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(TermsConfirmationState())
    val viewState: StateFlow<TermsConfirmationState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<TermsConfirmationEvent>()
    val viewEvent: SharedFlow<TermsConfirmationEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError()

    fun onUiAction(action: TermsConfirmationUiAction) {
        when (action) {
            is TermsConfirmationUiAction.OnButtonClick -> onAcceptTerms()
            else -> Unit
        }
    }

    private fun onAcceptTerms() {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(errorHandler) {
            createUserUseCase()
            _viewEvent.emit(TermsConfirmationEvent.NavigateToDashboard)
            _viewState.update { it.copy(isLoading = false) }
        }
    }
}
