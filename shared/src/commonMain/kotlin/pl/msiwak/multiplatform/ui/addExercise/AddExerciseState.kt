package pl.msiwak.multiplatform.ui.addExercise

import org.example.library.MR
import pl.msiwak.multiplatform.data.common.DateFilter
import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.UnitType

data class AddExerciseState(
    var exerciseTitle: String = "",
    var results: List<FormattedResultData> = listOf(),
    val newResultData: FormattedResultData = FormattedResultData(),
    val isResultFieldEnabled: Boolean = false,
    val isRemoveExerciseDialogVisible: Boolean = false,
    val filter: List<DateFilter> = listOf(
        DateFilter(MR.strings.filter_day),
        DateFilter(MR.strings.filter_week),
        DateFilter(MR.strings.filter_month),
        DateFilter(MR.strings.filter_year)
    ),
    val selectedFilterPosition: Int = 0,
    val unitType: UnitType = UnitType.METRIC,
    val unit: String = "kg",
    val resultDataTitles: List<String> = emptyList()
)