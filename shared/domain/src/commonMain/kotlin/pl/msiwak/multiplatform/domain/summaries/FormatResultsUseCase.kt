package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter

class FormatResultsUseCase(
    private val dateFormatter: DateFormatter,
    private val numberFormatter: NumberFormatter,
    private val getUnitsUseCase: GetUnitsUseCase
) {
    operator fun invoke(params: Params): List<FormattedResultData> {
        val unit = getUnitsUseCase()
        return params.results.map {
            val calculatedResult = if (unit == UnitType.IMPERIAL) {
                it.result.toDouble() * params.exerciseType.convertValue
            } else {
                it.result.toDouble()
            }
            FormattedResultData(
                numberFormatter.formatNumber(calculatedResult),
                it.amount,
                dateFormatter.formatDate(it.date)
            )
        }
    }

    data class Params(val results: List<ResultData>, val exerciseType: ExerciseType)
}
