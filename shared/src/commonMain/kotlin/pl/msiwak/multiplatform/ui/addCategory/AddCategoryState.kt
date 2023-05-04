package pl.msiwak.multiplatform.ui.addCategory

import pl.msiwak.multiplatform.data.common.ExerciseType

data class AddCategoryState(
    var name: String = "",
    var exerciseType: ExerciseType = ExerciseType.GYM
)