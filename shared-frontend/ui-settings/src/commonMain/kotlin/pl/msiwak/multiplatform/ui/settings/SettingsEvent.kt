package pl.msiwak.multiplatform.ui.settings

sealed class SettingsEvent {
    data object Logout : SettingsEvent()
    data object NavigateToAdminPanel : SettingsEvent()
}
