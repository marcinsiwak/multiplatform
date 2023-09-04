package pl.msiwak.multiplatform.core.ui.addCategory

import pl.msiwak.multiplatform.core.data.common.ExerciseType

data class AddCategoryState(
    var name: String = "",
    var exerciseType: ExerciseType = ExerciseType.GYM
)