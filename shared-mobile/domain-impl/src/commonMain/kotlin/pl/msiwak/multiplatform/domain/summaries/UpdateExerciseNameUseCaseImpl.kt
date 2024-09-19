package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class UpdateExerciseNameUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : UpdateExerciseNameUseCase {
    override suspend operator fun invoke(exercise: Exercise) {
        categoryRepository.updateExerciseName(exercise)
    }
}
