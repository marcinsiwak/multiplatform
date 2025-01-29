package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class AddExerciseUseCaseImpl(private val categoryRepository: CategoryRepository) : AddExerciseUseCase {
    override suspend operator fun invoke(params: AddExerciseUseCase.Params): String {
        return with(params) {
            categoryRepository.addExercise(categoryId, name, exerciseType)
        }
    }
}
