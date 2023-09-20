package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.ExerciseRepository

class RemoveExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(id: String) {
        exerciseRepository.removeExercise(id)
    }
}