@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.commonObject

import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.password_requirements_capital
import athletetrack.shared.commonresources.generated.resources.password_requirements_letter
import athletetrack.shared.commonresources.generated.resources.password_requirements_more_than_eight
import athletetrack.shared.commonresources.generated.resources.password_requirements_number
import athletetrack.shared.commonresources.generated.resources.password_requirements_special
import org.jetbrains.compose.resources.ExperimentalResourceApi

enum class PasswordRequirementType(val stringResource: org.jetbrains.compose.resources.StringResource) {
    LENGTH(Res.string.password_requirements_more_than_eight),
    NUMBER(Res.string.password_requirements_number),
    LETTER(Res.string.password_requirements_letter),
    CAPITAL(Res.string.password_requirements_capital),
    SPECIAL(Res.string.password_requirements_special)
}
