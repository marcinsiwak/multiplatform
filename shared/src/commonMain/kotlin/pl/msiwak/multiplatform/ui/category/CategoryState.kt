package pl.msiwak.multiplatform.ui.category

import pl.msiwak.multiplatform.data.common.ExerciseShort

data class CategoryState(
    val exerciseList: List<ExerciseShort> = emptyList(),
    val isDialogVisible: Boolean = false,
    val newExerciseName: String = "",
    val isRemoveExerciseDialogVisible: Boolean = false
)