package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.UnitType
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.repository.ExerciseRepository

class UpdateExerciseUseCase(
    private val exerciseRepository: ExerciseRepository,
    private val getUnitsUseCase: GetUnitsUseCase
) {
    suspend operator fun invoke(exerciseData: ExerciseData) {
        val unit = getUnitsUseCase()
        if (unit == UnitType.IMPERIAL) {
            val newResults = exerciseData.results.map {
                it.copy(result = it.result.div(exerciseData.exerciseType.convertValue))
            }
            val newExerciseData = exerciseData.copy(results = newResults)
            exerciseRepository.updateExercise(newExerciseData)
            return
        }
        exerciseRepository.updateExercise(exerciseData)
    }
}