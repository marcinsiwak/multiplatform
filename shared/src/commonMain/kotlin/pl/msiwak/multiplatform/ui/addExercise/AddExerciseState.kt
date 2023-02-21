package pl.msiwak.multiplatform.ui.addExercise

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData

data class AddExerciseState(
    val exerciseTitle: String = "",
    val newResult: String = "",
    val newResultDate: String = "",
    val results: List<ResultData> = listOf(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)