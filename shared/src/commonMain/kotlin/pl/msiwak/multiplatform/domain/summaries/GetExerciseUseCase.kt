package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.UnitType
import pl.msiwak.multiplatform.data.common.ExerciseWithUnit
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase

class GetExerciseUseCase(
    private val getExerciseDataUseCase: GetExerciseDataUseCase,
    private val getUnitsUseCase: GetUnitsUseCase
) {
    suspend operator fun invoke(id: String): ExerciseWithUnit {
        val exercise = getExerciseDataUseCase(id)
        val unitType = getUnitsUseCase()
        if (unitType == UnitType.IMPERIAL) {
            val newExercise = exercise?.copy(
                results = exercise.results.map {
                    it.copy(result = it.result.times(exercise.exerciseType.convertValue))
                }
            )
            return ExerciseWithUnit(newExercise, exercise?.exerciseType?.unitImperial)
        }

        return ExerciseWithUnit(exercise, exercise?.exerciseType?.unitMetric)
    }
}