package pl.msiwak.multiplatform.ui.settings

data class SettingsState(
    val versionName: String = "",
    val isLogoutButtonVisible: Boolean = true,
    val isAdmin: Boolean = false,
    val isDeleteAccountPopupVisible: Boolean = false,
    val isLoading: Boolean = false
)
