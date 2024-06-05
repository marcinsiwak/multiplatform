package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ChartResultData
import pl.msiwak.multiplatform.commonObject.ExerciseType

interface GetResultsUseCase {
    suspend operator fun invoke(
        exerciseId: String,
        exerciseType: ExerciseType
    ): List<ChartResultData>
}
