package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class VerifyEmailViewModel(
    private val resendVerificationEmailUseCase: ResendVerificationEmailUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(VerifyState())
    val viewState: StateFlow<VerifyState> = _viewState.asStateFlow()

    private val errorHandler = globalErrorHandler.handleError()

    fun onUiAction(action: VerifyEmailUiAction) {
        when (action) {
            VerifyEmailUiAction.OnResendMailClicked -> onResendMailClicked()
            else -> Unit
        }
    }

    private fun onResendMailClicked() = viewModelScope.launch(errorHandler) {
        // todo add timer to resend
        resendVerificationEmailUseCase()
    }
}
