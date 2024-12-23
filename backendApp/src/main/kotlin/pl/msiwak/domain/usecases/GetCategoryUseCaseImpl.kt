package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory

class GetCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiCategoryMapper: ApiCategoryMapper
) : GetCategoryUseCase {
    override suspend operator fun invoke(id: String): ApiCategory? {
        val categoryEntity = exerciseRepository.getCategory(id) ?: return null
        return apiCategoryMapper(categoryEntity)
    }
}
