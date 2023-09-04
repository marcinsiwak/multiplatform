package pl.msiwak.multiplatform.core.ui.addExercise

import pl.msiwak.multiplatform.core.data.common.DateFilterType
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.core.data.common.FormattedResultData
import pl.msiwak.multiplatform.core.data.common.SortType
import pl.msiwak.multiplatform.core.data.common.UnitType

data class AddExerciseState(
    var exerciseTitle: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    var results: List<FormattedResultData> = listOf(),
    val newResultData: FormattedResultData = FormattedResultData(),
    val isResultFieldEnabled: Boolean = false,
    val isRemoveExerciseDialogVisible: Boolean = false,
    val filter: List<DateFilterType> = listOf(
        DateFilterType.ALL,
        DateFilterType.DAY,
        DateFilterType.WEEK,
        DateFilterType.MONTH,
        DateFilterType.YEAR
    ),
    val selectedFilterPosition: Int = 0,
    val unitType: UnitType = UnitType.METRIC,
    val unit: String = "kg",
    val resultDataTitles: List<String> = emptyList(),
    val sortType: SortType? = null
)