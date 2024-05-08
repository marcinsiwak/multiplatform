package pl.msiwak.multiplatform.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.navigator.NavigationDirections

class SettingsViewModel(
    getVersionNameUseCase: GetVersionNameUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isOfflineModeUseCase: GetIsOfflineModeUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SettingsState())
    val viewState: StateFlow<SettingsState> = _viewState

    init {
        viewModelScope.launch {
            val versionName = getVersionNameUseCase()
            _viewState.update {
                it.copy(
                    versionName = versionName,
                    isLogoutButtonVisible = !isOfflineModeUseCase()
                )
            }
        }
    }

    fun onLogoutClicked() {
        viewModelScope.launch {
            logoutUseCase()
//            navigator.navigate(NavigationDirections.Welcome(true))
        }
    }
}
