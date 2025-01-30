package pl.msiwak.multiplatform.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.domain.user.GetUsersUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class SettingsViewModel(
    getVersionNameUseCase: GetVersionNameUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(SettingsState())
    val viewState: StateFlow<SettingsState> = _viewState

    private val _viewEvent = MutableSharedFlow<SettingsEvent>()
    val viewEvent: SharedFlow<SettingsEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            val user = getUserUseCase()
            _viewState.update {
                it.copy(
                    versionName = getVersionNameUseCase(),
                    isAdmin = user.role == Role.ADMIN
                )
            }
        }
    }

    fun onUiAction(action: SettingsUiAction) {
        when (action) {
            SettingsUiAction.OnLogoutClicked -> onLogoutClicked()
            SettingsUiAction.OnAdminPanelClicked -> onAdminPanelClicked()
            else -> Unit
        }
    }

    private fun onLogoutClicked() {
        viewModelScope.launch {
            logoutUseCase()
            _viewEvent.emit(SettingsEvent.Logout)
        }
    }

    private fun onAdminPanelClicked() {
        viewModelScope.launch {
            _viewEvent.emit(SettingsEvent.NavigateToAdminPanel)
        }
    }
}
