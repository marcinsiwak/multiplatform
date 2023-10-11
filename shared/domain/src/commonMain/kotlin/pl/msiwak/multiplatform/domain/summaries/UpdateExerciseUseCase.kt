package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase

class UpdateExerciseUseCase(
    private val getUnitsUseCase: GetUnitsUseCase
) {
    suspend operator fun invoke(exercise: Exercise) {
//        val unit = getUnitsUseCase()
//        if (unit == UnitType.IMPERIAL) {
//            val newResults = exercise.results.map {
//                it.copy(result = it.result.div(exercise.exerciseType.convertValue))
//            }
//            val newExerciseData = exercise.copy(results = newResults)
//            exerciseRepository.updateExercise(newExerciseData)
//            return
//        }
//        exerciseRepository.updateExercise(exercise)
    }
}