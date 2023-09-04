package pl.msiwak.multiplatform.core.ui.category

import pl.msiwak.multiplatform.core.data.common.Exercise
import pl.msiwak.multiplatform.core.data.common.ExerciseType

data class CategoryState(
    val categoryName: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    var exerciseList: List<Exercise> = emptyList(),
    var isDialogVisible: Boolean = false,
    var newExerciseName: String = "",
    val isRemoveExerciseDialogVisible: Boolean = false
)