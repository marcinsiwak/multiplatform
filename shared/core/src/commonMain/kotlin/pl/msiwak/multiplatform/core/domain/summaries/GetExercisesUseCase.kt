package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.core.data.common.Exercise
import pl.msiwak.multiplatform.core.repository.ExerciseRepository

class GetExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(): List<Exercise> {
        return exerciseRepository.getExercises()
    }
}
