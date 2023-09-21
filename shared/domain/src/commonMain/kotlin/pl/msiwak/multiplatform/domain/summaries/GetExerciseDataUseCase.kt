package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.ExerciseRepository

class GetExerciseDataUseCase(
    private val exerciseRepository: ExerciseRepository
) {
    suspend operator fun invoke(id: String): Exercise? {
        return exerciseRepository.getExercise(id)
    }
}