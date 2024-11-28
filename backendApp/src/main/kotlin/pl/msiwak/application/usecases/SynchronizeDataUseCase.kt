package pl.msiwak.application.usecases

import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult

interface SynchronizeDataUseCase {
    suspend operator fun invoke(
        categoriesDTO: List<ApiCategory>,
        exercisesDTO: List<ApiExercise>,
        resultsDTO: List<ApiResult>,
        userId: String
    )
}
