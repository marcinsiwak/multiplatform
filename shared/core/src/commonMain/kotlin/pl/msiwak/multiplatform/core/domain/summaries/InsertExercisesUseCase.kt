package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.repository.ExerciseRepository

class InsertExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(summaries: List<Exercise>) {
        exerciseRepository.insertExercises(summaries)
    }
}