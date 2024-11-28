package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory

class GetCategoriesUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiCategoryMapper: ApiCategoryMapper
) : GetCategoriesUseCase {
    override suspend operator fun invoke(userId: String): List<ApiCategory> {
        return exerciseRepository.getCategories(userId)
            .map { category ->
                apiCategoryMapper(category)
            }
    }
}
