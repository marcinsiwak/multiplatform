package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.ResultData

interface AddResultUseCase {
    suspend operator fun invoke(params: Params)

    data class Params(val result: ResultData, val exerciseType: ExerciseType)
}
