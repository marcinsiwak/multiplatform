package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveExerciseUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String): Flow<Exercise> {
        return categoryRepository.observeExercise(id)
    }
}