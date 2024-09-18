package pl.msiwak.multiplatform.ui.settings

sealed class SettingsUiAction {
    data object OnUnitClicked : SettingsUiAction()
    data object OnLanguageClicked : SettingsUiAction()
    data object OnLogoutClicked : SettingsUiAction()
}
