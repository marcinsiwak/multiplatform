package pl.msiwak.multiplatform.ui.register

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.commonObject.PasswordRequirement
import pl.msiwak.multiplatform.commonObject.PasswordRequirementType

data class RegisterState(
    var login: String = "",
    var password: String = "",
    val loginErrorMessage: StringResource? = null,
    val passwordErrorMessage: StringResource? = null,
    val isPasswordVisible: Boolean = false,
    val passwordRequirements: List<PasswordRequirement> = listOf(
        PasswordRequirement(false, PasswordRequirementType.LENGTH),
        PasswordRequirement(false, PasswordRequirementType.LETTER),
        PasswordRequirement(false, PasswordRequirementType.SPECIAL),
        PasswordRequirement(false, PasswordRequirementType.CAPITAL),
        PasswordRequirement(false, PasswordRequirementType.NUMBER),
    ),
)