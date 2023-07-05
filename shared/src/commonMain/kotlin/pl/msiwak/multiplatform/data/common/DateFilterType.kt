package pl.msiwak.multiplatform.data.common

//import dev.icerock.moko.resources.StringResource
//import pl.msiwak.multiplatform.MR

enum class DateFilterType(val nameResourceId: String) {
    ALL("MR.strings.filter_all"),
    DAY("MR.strings.filter_day"),
    WEEK("MR.strings.filter_week"),
    MONTH("MR.strings.filter_month"),
    YEAR("MR.strings.filter_year");
}