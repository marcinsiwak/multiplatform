package pl.msiwak.multiplatform.ui.addCategory

import pl.msiwak.multiplatform.data.common.ExerciseType

data class AddCategoryState(
    val name: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM
)