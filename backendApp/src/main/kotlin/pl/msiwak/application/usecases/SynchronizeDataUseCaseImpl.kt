package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.domain.entities.ExerciseEntity
import pl.msiwak.domain.entities.ResultEntity
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO

class SynchronizeDataUseCaseImpl(private val exercisesRepository: ExerciseRepository) : SynchronizeDataUseCase {
    override suspend fun invoke(
        categoriesDTO: List<CategoryDTO>,
        exercisesDTO: List<ExerciseDTO>,
        resultsDTO: List<ResultDTO>,
        userId: String
    ) {
        val syncCategories = categoriesDTO
            .map { category ->
                val categoryEntity =
                    CategoryEntity(userId = userId, name = category.name, exerciseType = category.exerciseType)
                exercisesDTO
                    .filter { it.categoryId == category.id }
                    .map { exercise ->
                        if (categoryEntity.id.isNullOrEmpty()) return
                        val exerciseEntity = ExerciseEntity(exercise.name, categoryEntity.id)
                        resultsDTO
                            .filter { it.exerciseId == exercise.id }
                            .map { result ->
                                if (exerciseEntity.id.isNullOrEmpty()) return
                                exerciseEntity.addResult(
                                    ResultEntity(
                                        exerciseId = exerciseEntity.id,
                                        amount = result.amount,
                                        result = result.result,
                                        date = result.date
                                    )
                                )
                            }
                        categoryEntity.addExercise(exerciseEntity)
                    }
                categoryEntity
            }

        exercisesRepository.synchronizeData(syncCategories)
    }
}