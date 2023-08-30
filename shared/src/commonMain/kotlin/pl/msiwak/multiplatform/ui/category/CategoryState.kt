package pl.msiwak.multiplatform.ui.category

import pl.msiwak.multiplatform.data.common.Exercise
import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType

data class CategoryState(
    val categoryName: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    val exerciseList: List<Exercise> = emptyList(),
    var isDialogVisible: Boolean = false,
    var newExerciseName: String = "",
    val isRemoveExerciseDialogVisible: Boolean = false
)