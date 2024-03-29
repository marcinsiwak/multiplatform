package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class VerifyEmailViewModel(
    private val resendVerificationEmailUseCase: ResendVerificationEmailUseCase,
    private val navigator: Navigator,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewEvent = MutableSharedFlow<VerifyEmailEvent>()
    val viewEvent: SharedFlow<VerifyEmailEvent> = _viewEvent.asSharedFlow()

    private val _viewState = MutableStateFlow(VerifyState())
    val viewState: StateFlow<VerifyState> = _viewState.asStateFlow()

    private val errorHandler = globalErrorHandler.handleError()

    fun onOpenMailClicked() = viewModelScope.launch {
        _viewEvent.emit(VerifyEmailEvent.OpenMail)
    }

    fun onResendMailClicked() = viewModelScope.launch(errorHandler) {
        resendVerificationEmailUseCase()
    }

    fun onLoginClicked() {
        navigator.navigate(NavigationDirections.Welcome(true))
    }
}
