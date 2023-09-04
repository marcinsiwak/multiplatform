package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.core.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.core.utils.DateFormatter
import pl.msiwak.multiplatform.core.utils.NumberFormatter

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