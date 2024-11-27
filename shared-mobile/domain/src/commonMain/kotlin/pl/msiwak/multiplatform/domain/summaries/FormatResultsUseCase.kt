package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.ResultData

interface FormatResultsUseCase {
    operator fun invoke(params: Params): List<FormattedResultData>

    data class Params(val results: List<ResultData>, val exerciseType: ExerciseType)
}
