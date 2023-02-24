package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.utils.DateFormatter

class FormatResultsUseCase(private val dateFormatter: DateFormatter) {
    operator fun invoke(resultData: List<ResultData>): List<FormattedResultData> {
        return resultData.map {
            FormattedResultData(it.result, it.amount.toString(), dateFormatter.formatDate(it.date))
        }
    }
}