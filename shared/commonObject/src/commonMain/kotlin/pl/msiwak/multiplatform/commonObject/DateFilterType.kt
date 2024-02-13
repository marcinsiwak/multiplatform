package pl.msiwak.multiplatform.commonObject

import dev.icerock.moko.resources.StringResource
import pl.msiwak.multiplatform.commonResources.SR

enum class DateFilterType(val nameResourceId: StringResource) {
    ALL(SR.strings.filter_all),
    DAY(SR.strings.filter_day),
    WEEK(SR.strings.filter_week),
    MONTH(SR.strings.filter_month),
    YEAR(SR.strings.filter_year);
}
