package pl.msiwak.multiplatform.ui.verifyEmail

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class VerifyEmailViewModel(
    private val resendVerificationEmailUseCase: ResendVerificationEmailUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewEvent = MutableSharedFlow<VerifyEmailEvent>()
    val viewEvent: SharedFlow<VerifyEmailEvent> = _viewEvent.asSharedFlow()

    fun onOpenMailClicked() = viewModelScope.launch {
        _viewEvent.emit(VerifyEmailEvent.OpenMail)
    }

    fun onResendMailClicked() = viewModelScope.launch {
        resendVerificationEmailUseCase()
    }

    fun onLoginClicked() {
        navigator.navigate(NavigationDirections.Welcome(true))
    }
}
