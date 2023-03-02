package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.repository.ExerciseRepository

class UpdateExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(exerciseData: ExerciseData) {
        exerciseRepository.updateExercise(exerciseData)
    }
}