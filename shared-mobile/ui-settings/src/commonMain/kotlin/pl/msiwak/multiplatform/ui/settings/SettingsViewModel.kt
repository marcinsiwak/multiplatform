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
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase

class SettingsViewModel(
    getVersionNameUseCase: GetVersionNameUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SettingsState())
    val viewState: StateFlow<SettingsState> = _viewState

    private val _viewEvent = MutableSharedFlow<SettingsEvent>()
    val viewEvent: SharedFlow<SettingsEvent> = _viewEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    versionName = getVersionNameUseCase()
                )
            }
        }
    }

    fun onUiAction(action: SettingsUiAction) {
        when (action) {
            SettingsUiAction.OnLogoutClicked -> onLogoutClicked()
            else -> Unit
        }
    }

    private fun onLogoutClicked() {
        viewModelScope.launch {
            logoutUseCase()
            _viewEvent.emit(SettingsEvent.Logout)
        }
    }
}
