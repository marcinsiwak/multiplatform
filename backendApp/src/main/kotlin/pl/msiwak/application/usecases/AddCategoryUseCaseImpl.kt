package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiCategoryMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory

class AddCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiCategoryMapper: ApiCategoryMapper
) : AddCategoryUseCase {
    override suspend operator fun invoke(name: String, exerciseType: String, userId: String): ApiCategory {
        val category = CategoryEntity(
            userId = userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
        return apiCategoryMapper(category)
    }
}
