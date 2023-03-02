package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.repository.ExerciseRepository

class InsertExercisesUseCase(private val exerciseRepository: ExerciseRepository) {
    suspend operator fun invoke(summaries: List<ExerciseData>) {
        exerciseRepository.insertExercises(summaries)
    }
}