package pl.msiwak.multiplatform.core.ui.settings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.core.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.core.ui.navigator.Navigator

class SettingsViewModel(
    private val navigator: Navigator,
    getVersionNameUseCase: GetVersionNameUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(SettingsState())
    val viewState: StateFlow<SettingsState> = _viewState

    init {
        val versionName = getVersionNameUseCase()
        _viewState.value = _viewState.value.copy(versionName = versionName)
    }

    fun onLanguageClicked() {
        navigator.navigate(NavigationDirections.Language)
    }

    fun onUnitClicked() {
        navigator.navigate(NavigationDirections.Unit)
    }

    fun onLogoutClicked() {
        viewModelScope.launch {
            logoutUseCase()
            navigator.navigate(NavigationDirections.Welcome(true))
        }
    }
}