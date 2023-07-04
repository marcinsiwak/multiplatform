package pl.msiwak.multiplatform.ui.settings

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsDiHelper : KoinComponent {
    private val settingsVM: SettingsViewModel by inject()
    fun getViewModel(): SettingsViewModel = settingsVM
}