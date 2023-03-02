package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.repository.ExerciseRepository

class InsertExerciseUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(exerciseData: ExerciseData): Long {
        return exerciseRepository.insertExercise(exerciseData)
    }
}