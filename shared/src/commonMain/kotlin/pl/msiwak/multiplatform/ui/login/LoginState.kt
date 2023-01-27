package pl.msiwak.multiplatform.ui.login

data class LoginState(
    val login: String = "",
    val password: String = "",
    val authErrorMessage: String? = null,
)