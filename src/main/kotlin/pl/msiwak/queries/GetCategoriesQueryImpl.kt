package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.repositories.ExerciseRepository

class GetCategoriesQueryImpl(private val exerciseRepository: ExerciseRepository) : GetCategoriesQuery {
    override suspend fun invoke(userId: String): List<CategoryDTO> {
        return exerciseRepository.getCategories(userId)
            .map {
                CategoryDTO(
                    id = it.id,
                    name = it.name,
                    exerciseType = it.exerciseType
                )
            }
    }
}