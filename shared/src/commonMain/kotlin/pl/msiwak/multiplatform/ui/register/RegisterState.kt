package pl.msiwak.multiplatform.ui.register

import dev.icerock.moko.resources.StringResource

data class RegisterState(
    val login: String = "",
    val password: String = "",
    val loginErrorMessage: StringResource? = null,
    val passwordErrorMessage: StringResource? = null,
)