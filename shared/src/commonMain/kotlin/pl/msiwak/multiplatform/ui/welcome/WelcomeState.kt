package pl.msiwak.multiplatform.ui.welcome

data class WelcomeState(
    var login: String = "",
    var password: String = "",
    val authErrorMessage: String? = null,
    val isPasswordVisible: Boolean = false
)
