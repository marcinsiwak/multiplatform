package pl.msiwak.multiplatform.ui.addCategory

import pl.msiwak.multiplatform.commonObject.ExerciseType

data class AddCategoryState(
    var name: String = "",
    var exerciseType: ExerciseType = ExerciseType.GYM
)