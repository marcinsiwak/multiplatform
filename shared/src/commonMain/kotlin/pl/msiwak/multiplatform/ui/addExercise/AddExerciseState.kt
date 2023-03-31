package pl.msiwak.multiplatform.ui.addExercise

import pl.msiwak.multiplatform.data.common.FormattedResultData

data class AddExerciseState(
    var exerciseTitle: String = "",
    var results: List<FormattedResultData> = listOf(),
    val newResultData: FormattedResultData = FormattedResultData(),
    val isResultFieldEnabled: Boolean = false,
    val isRemoveExerciseDialogVisible: Boolean = false
)