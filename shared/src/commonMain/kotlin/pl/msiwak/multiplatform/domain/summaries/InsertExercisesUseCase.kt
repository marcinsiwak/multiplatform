package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Exercise
import pl.msiwak.multiplatform.repository.ExerciseRepository

class InsertExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(summaries: List<Exercise>) {
        exerciseRepository.insertExercises(summaries)
    }
}