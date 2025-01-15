package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private var job: Job? = null

    init {
        startTimer()
    }

    fun onUiAction(action: VerifyEmailUiAction) {
        when (action) {
            VerifyEmailUiAction.OnResendMailClicked -> onResendMailClicked()
            else -> Unit
        }
    }

    private fun onResendMailClicked() {
        viewModelScope.launch(errorHandler) {
            startTimer()
            resendVerificationEmailUseCase()
        }
    }

    private fun startTimer() {
        _viewState.update { it.copy(resendDelay = 60) }
        job = CoroutineScope(Dispatchers.Default).launch(errorHandler) {
            var isTimerActive = true
            while (isTimerActive) {
                delay(1000)
                if (viewState.value.resendDelay > 0) {
                    _viewState.update { it.copy(resendDelay = it.resendDelay.minus(1)) }
                } else {
                    isTimerActive = false
                    job?.cancel()
                }
            }
        }
    }
}
