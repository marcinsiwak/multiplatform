package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.repository.ExerciseRepository

class RemoveExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(id: Long) {
        exerciseRepository.removeExercise(id)
    }
}