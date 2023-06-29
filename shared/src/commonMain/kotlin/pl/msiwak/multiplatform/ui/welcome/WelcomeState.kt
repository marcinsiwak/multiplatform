package pl.msiwak.multiplatform.ui.welcome

data class WelcomeState(
    val login: String = "",
    val password: String = "",
    val authErrorMessage: String? = null,
)
