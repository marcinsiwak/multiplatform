package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.repository.ExerciseRepository

class GetExerciseDataUseCase(
    private val exerciseRepository: ExerciseRepository
) {
    suspend operator fun invoke(id: String): Exercise? {
        return exerciseRepository.getExercise(id)
    }
}