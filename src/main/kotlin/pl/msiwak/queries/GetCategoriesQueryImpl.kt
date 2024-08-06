package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.dtos.ExerciseDTO
import pl.msiwak.dtos.ResultDTO
import pl.msiwak.repositories.ExerciseRepository

class GetCategoriesQueryImpl(private val exerciseRepository: ExerciseRepository) : GetCategoriesQuery {
    override suspend fun invoke(userId: String): List<CategoryDTO> {
        return exerciseRepository.getCategories(userId)
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
                            exerciseType = category.exerciseType,
                            results = exercise.results.map {
                                ResultDTO(
                                    id = it.id,
                                    exerciseId = exercise.id!!,
                                    amount = it.amount,
                                    result = it.result,
                                    date = it.date
                                )
                            }
                        )
                    }
                )
            }
    }
}