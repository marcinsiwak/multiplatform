package pl.msiwak.multiplatform.network.mapper

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiResult

class ResultMapper : Mapper<ApiResult, ResultData>() {

    override fun map(value: ApiResult): ResultData {
        return ResultData(
            result = value.result,
            amount = value.amount.toString(),
            date = value.date.toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }
}