package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType

interface CreateCategoryUseCase {
    suspend operator fun invoke(params: Params)

    data class Params(val name: String, val exerciseType: ExerciseType)
}
