package pl.msiwak.multiplatform.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class DashboardViewModel(
    val bottomNavigationProvider: BottomNavigationProvider,
    private val getUserUseCase: GetUserUseCase,
    private val globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(DashboardState())
    val viewState: StateFlow<DashboardState> = _viewState.asStateFlow()

    fun onUiAction(action: DashboardUiAction) {
        when (action) {
            is DashboardUiAction.OnTabChanges -> onTabChanges(action.pos)
            else -> Unit
        }
    }

    private fun onTabChanges(pos: Int) {
        _viewState.update { it.copy(selectedTabIndex = pos) }
    }
}
