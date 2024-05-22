@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.commonObject

import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.filter_all
import athletetrack.shared.commonresources.generated.resources.filter_day
import athletetrack.shared.commonresources.generated.resources.filter_month
import athletetrack.shared.commonresources.generated.resources.filter_week
import athletetrack.shared.commonresources.generated.resources.filter_year
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

enum class DateFilterType(val nameResourceId: StringResource) {
    ALL(Res.string.filter_all),
    DAY(Res.string.filter_day),
    WEEK(Res.string.filter_week),
    MONTH(Res.string.filter_month),
    YEAR(Res.string.filter_year);
}
