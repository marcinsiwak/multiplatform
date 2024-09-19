package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class AddExerciseUseCaseImpl(private val categoryRepository: CategoryRepository) :
    AddExerciseUseCase {
    override suspend operator fun invoke(exercise: Exercise): String {
        return categoryRepository.addExercise(exercise)
    }
}
