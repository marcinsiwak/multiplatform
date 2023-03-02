package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.repository.ExerciseRepository

class GetExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(): List<ExerciseData> {
        return exerciseRepository.getExercises()
    }
}
