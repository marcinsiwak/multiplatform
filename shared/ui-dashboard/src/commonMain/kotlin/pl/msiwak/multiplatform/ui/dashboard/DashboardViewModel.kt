package pl.msiwak.multiplatform.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class DashboardViewModel(
    private val getUser: GetUserUseCase,
    getIsOfflineModeUseCase: GetIsOfflineModeUseCase,
    globalErrorHandler: GlobalErrorHandler,
    val bottomNavigationProvider: BottomNavigationProvider
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
        }
    }

    fun onTabChanges(pos: Int) {
        _viewState.update { it.copy(selectedTabIndex = pos) }
    }
}
