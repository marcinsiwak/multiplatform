package pl.msiwak.multiplatform.ui.settings

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SettingsViewModel(
    private val navigator: Navigator,
    getVersionNameUseCase: GetVersionNameUseCase
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
}