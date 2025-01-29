package pl.msiwak.multiplatform.ui.adminpanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.user.GetUsersUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class AdminPanelViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(AdminPanelState())
    val viewState: StateFlow<AdminPanelState> = _viewState

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            val users = getUsersUseCase()
            _viewState.update { it.copy(users = users) }
        }
    }

    fun onUiAction(action: AdminPanelUiAction) {

    }
}
