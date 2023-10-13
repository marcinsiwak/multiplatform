package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveExerciseUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String) {
        categoryRepository.observeExercise(id)
    }
}