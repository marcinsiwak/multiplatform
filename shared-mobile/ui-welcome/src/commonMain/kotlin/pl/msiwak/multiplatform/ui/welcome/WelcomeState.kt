package pl.msiwak.multiplatform.ui.welcome

data class WelcomeState(
    var login: String = "",
    var password: String = "",
    val authErrorMessage: String? = null,
    val isPasswordVisible: Boolean = false,
    val isErrorDialogVisible: Boolean = false,
    val isLoading: Boolean = false,
    var isSynchronizationDialogVisible: Boolean = false
)
