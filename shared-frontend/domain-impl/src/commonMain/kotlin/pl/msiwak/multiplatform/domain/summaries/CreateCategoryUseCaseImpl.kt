package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class CreateCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    CreateCategoryUseCase {
    override suspend operator fun invoke(params: CreateCategoryUseCase.Params) = with(params) {
        categoryRepository.createCategory(name, exerciseType)
    }
}
