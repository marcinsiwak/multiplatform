package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Exercise
import pl.msiwak.multiplatform.repository.ExerciseRepository

class GetExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(): List<Exercise> {
        return exerciseRepository.getExercises()
    }
}
