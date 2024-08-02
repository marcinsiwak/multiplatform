package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.dtos.ExerciseDTO
import pl.msiwak.repositories.CategoryRepository

class GetCategoriesQueryImpl(private val categoryRepository: CategoryRepository) : GetCategoriesQuery {
    override suspend fun invoke(userId: String): List<CategoryDTO> {
        return categoryRepository.getCategories(userId)
            .map { category ->
                CategoryDTO(
                    id = category.id,
                    name = category.name,
                    exerciseType = category.exerciseType,
                    exercises = category.exercises.map { exercise ->
                        ExerciseDTO(
                            id = exercise.id,
                            categoryId = category.id!!,
                            name = exercise.name,
                            exerciseType = category.exerciseType
                        )
                    }
                )
            }
    }
}