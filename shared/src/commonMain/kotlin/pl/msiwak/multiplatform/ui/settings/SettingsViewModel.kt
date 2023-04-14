package pl.msiwak.multiplatform.ui.settings

import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SettingsViewModel(private val navigator: Navigator): ViewModel() {

    fun onLanguageClicked() {
        navigator.navigate(NavigationDirections.Language)
    }
    fun onUnitClicked() {
        navigator.navigate(NavigationDirections.Unit)
    }
}