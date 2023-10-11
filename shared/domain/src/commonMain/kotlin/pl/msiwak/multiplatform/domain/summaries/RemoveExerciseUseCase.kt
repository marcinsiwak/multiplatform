package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveExerciseUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(exercise: Exercise) = categoryRepository.removeExercise(exercise)
}