package pl.msiwak.multiplatform.ui.register

import dev.icerock.moko.resources.StringResource

data class RegisterState(
    var login: String = "",
    var password: String = "",
    val loginErrorMessage: StringResource? = null,
    val passwordErrorMessage: StringResource? = null,
    val isPasswordVisible: Boolean = false
)