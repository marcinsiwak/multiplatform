package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO

interface SynchronizeDataUseCase {
    suspend operator fun invoke(
        categoriesDTO: List<CategoryDTO>,
        exercisesDTO: List<ExerciseDTO>,
        resultsDTO: List<ResultDTO>,
        userId: String
    )
}