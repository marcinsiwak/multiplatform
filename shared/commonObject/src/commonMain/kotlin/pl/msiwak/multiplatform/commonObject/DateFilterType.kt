package pl.msiwak.multiplatform.commonObject

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.commonResources.MR

enum class DateFilterType(val nameResourceId: StringResource) {
    ALL(MR.strings.filter_all),
    DAY(MR.strings.filter_day),
    WEEK(MR.strings.filter_week),
    MONTH(MR.strings.filter_month),
    YEAR(MR.strings.filter_year);
}
