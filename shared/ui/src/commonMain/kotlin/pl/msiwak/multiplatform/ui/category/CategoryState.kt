package pl.msiwak.multiplatform.ui.category

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType

data class CategoryState(
    val categoryName: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    var exerciseList: List<Exercise> = emptyList(),
    var isDialogVisible: Boolean = false,
    var newExerciseName: String = "",
    val isRemoveExerciseDialogVisible: Boolean = false
)