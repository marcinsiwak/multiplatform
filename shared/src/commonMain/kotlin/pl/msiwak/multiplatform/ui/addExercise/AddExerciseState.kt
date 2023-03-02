package pl.msiwak.multiplatform.ui.addExercise

import pl.msiwak.multiplatform.data.common.FormattedResultData

data class AddExerciseState(
    val exerciseTitle: String = "",
    val results: List<FormattedResultData> = listOf(),
    val newResultData: FormattedResultData = FormattedResultData(),
    val isResultFieldEnabled: Boolean = false,
    val isRemoveExerciseDialogVisible: Boolean = false
)