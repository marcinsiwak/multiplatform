package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveExerciseUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String) = categoryRepository.removeExercise(id)
}