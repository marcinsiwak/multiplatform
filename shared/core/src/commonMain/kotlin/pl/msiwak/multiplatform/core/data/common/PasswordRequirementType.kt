package pl.msiwak.multiplatform.core.data.common

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.MR

enum class PasswordRequirementType(val stringResource: StringResource) {
    LENGTH(MR.strings.password_requirements_more_than_eight),
    NUMBER(MR.strings.password_requirements_number),
    LETTER(MR.strings.password_requirements_letter),
    CAPITAL(MR.strings.password_requirements_capital),
    SPECIAL(MR.strings.password_requirements_special),
}