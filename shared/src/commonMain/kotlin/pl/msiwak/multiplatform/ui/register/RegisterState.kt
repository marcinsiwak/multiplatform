package pl.msiwak.multiplatform.ui.register

data class RegisterState(
    val login: String = "",
    val password: String = "",
    val loginErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
)