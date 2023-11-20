package pl.msiwak.multiplatform.ui.dashboard

import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class DashboardViewModel(
    private val getUser: GetUserUseCase,
    private val navigator: Navigator,
    getIsOfflineModeUseCase: GetIsOfflineModeUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(DashboardState())
    val viewState: StateFlow<DashboardState> = _viewState.asStateFlow()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isOfflineBannerVisible = getIsOfflineModeUseCase()) }
        }
        viewModelScope.launch(errorHandler) {
            val user = getUser()
            Napier.e("OUTPUT, ${user.email}")
        }
    }

    fun onSignInUpClicked() {
        navigator.navigate(NavigationDirections.Welcome(false))
    }
}