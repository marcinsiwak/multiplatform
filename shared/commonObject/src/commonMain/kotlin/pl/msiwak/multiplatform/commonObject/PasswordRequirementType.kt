package pl.msiwak.multiplatform.commonObject

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.commonResources.MR

enum class PasswordRequirementType(val stringResource: StringResource) {
    LENGTH(MR.strings.password_requirements_more_than_eight),
    NUMBER(MR.strings.password_requirements_number),
    LETTER(MR.strings.password_requirements_letter),
    CAPITAL(MR.strings.password_requirements_capital),
    SPECIAL(MR.strings.password_requirements_special),
}
