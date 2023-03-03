package pl.msiwak.multiplatform.ui.category

import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType

data class CategoryState(
    val exerciseType: ExerciseType = ExerciseType.GYM,
    val exerciseList: List<ExerciseShort> = emptyList(),
    val isDialogVisible: Boolean = false,
    val newExerciseName: String = "",
    val isRemoveExerciseDialogVisible: Boolean = false
)