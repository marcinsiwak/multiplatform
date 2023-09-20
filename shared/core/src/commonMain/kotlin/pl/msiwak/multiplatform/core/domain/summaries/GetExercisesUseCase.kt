package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.ExerciseRepository

class GetExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(): List<Exercise> {
        return exerciseRepository.getExercises()
    }
}
