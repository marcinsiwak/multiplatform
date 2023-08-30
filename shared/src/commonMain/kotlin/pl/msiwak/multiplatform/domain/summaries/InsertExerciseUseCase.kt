package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Exercise
import pl.msiwak.multiplatform.repository.ExerciseRepository

class InsertExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(exercise: Exercise): Long {
        return exerciseRepository.insertExercise(exercise)
    }
}