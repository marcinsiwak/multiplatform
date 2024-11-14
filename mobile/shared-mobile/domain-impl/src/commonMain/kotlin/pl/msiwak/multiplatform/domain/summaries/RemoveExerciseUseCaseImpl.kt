package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveExerciseUseCaseImpl(private val categoryRepository: CategoryRepository) :
    RemoveExerciseUseCase {
    override suspend operator fun invoke(exercise: Exercise) =
        categoryRepository.removeExercise(exercise)
}
