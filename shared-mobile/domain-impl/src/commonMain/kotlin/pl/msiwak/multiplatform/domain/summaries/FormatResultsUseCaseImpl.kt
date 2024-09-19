package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter

class FormatResultsUseCaseImpl(
    private val dateFormatter: DateFormatter,
    private val numberFormatter: NumberFormatter,
    private val getUnitsUseCase: GetUnitsUseCase
) : FormatResultsUseCase {
    override fun invoke(params: FormatResultsUseCase.Params): List<FormattedResultData> {
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
}
