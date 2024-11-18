package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory

class GetCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val ApiCategoryMapper: ApiCategoryMapper
) : GetCategoryUseCase {
    override suspend operator fun invoke(id: String): ApiCategory? {
        val categoryEntity = exerciseRepository.getCategory(id) ?: return null
        return ApiCategoryMapper(categoryEntity)
    }
}