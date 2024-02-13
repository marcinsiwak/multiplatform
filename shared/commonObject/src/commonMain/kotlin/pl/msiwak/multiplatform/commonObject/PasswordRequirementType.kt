package pl.msiwak.multiplatform.commonObject

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.commonResources.SR

enum class PasswordRequirementType(val stringResource: StringResource) {
    LENGTH(SR.strings.password_requirements_more_than_eight),
    NUMBER(SR.strings.password_requirements_number),
    LETTER(SR.strings.password_requirements_letter),
    CAPITAL(SR.strings.password_requirements_capital),
    SPECIAL(SR.strings.password_requirements_special)
}
