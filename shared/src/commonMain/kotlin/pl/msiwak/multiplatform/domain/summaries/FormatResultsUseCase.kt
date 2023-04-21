package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter

class FormatResultsUseCase(
    private val dateFormatter: DateFormatter,
    private val numberFormatter: NumberFormatter
) {
    operator fun invoke(resultData: List<ResultData>): List<FormattedResultData> {
        return resultData.map {
            FormattedResultData(
                numberFormatter.formatNumber(it.result),
                it.amount,
                dateFormatter.formatDate(it.date)
            )
        }
    }

}