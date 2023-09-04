package pl.msiwak.multiplatform.core.ui.welcome

data class WelcomeState(
    var login: String = "",
    var password: String = "",
    val authErrorMessage: String? = null,
    val isPasswordVisible: Boolean = false,
    val isErrorDialogVisible: Boolean = false
)
