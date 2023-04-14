package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.repository.ExerciseRepository

class GetExerciseDataUseCase(
    private val exerciseRepository: ExerciseRepository
) {
    suspend operator fun invoke(id: Long): ExerciseData {
        return exerciseRepository.getExercise(id)
    }
}