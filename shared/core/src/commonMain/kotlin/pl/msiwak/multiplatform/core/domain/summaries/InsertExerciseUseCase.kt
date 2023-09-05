package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.repository.ExerciseRepository

class InsertExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(exercise: Exercise): Long {
        return exerciseRepository.insertExercise(exercise)
    }
}