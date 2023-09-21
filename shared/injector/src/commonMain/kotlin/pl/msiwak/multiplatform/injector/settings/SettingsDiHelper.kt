package pl.msiwak.multiplatform.injector.settings

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel

class SettingsDiHelper : KoinComponent {
    private val settingsVM: SettingsViewModel by inject()
    fun getViewModel(): SettingsViewModel = settingsVM
}