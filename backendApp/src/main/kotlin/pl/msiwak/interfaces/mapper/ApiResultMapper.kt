package pl.msiwak.interfaces.mapper

import pl.msiwak.infrastructure.entities.ResultEntity
import pl.msiwak.multiplatform.shared.model.ApiResult

class ApiResultMapper : Mapper<ResultEntity, ApiResult>() {
    override fun map(value: ResultEntity): ApiResult = with(value) {
        ApiResult(
            id = id,
            exerciseId = exerciseId!!,
            amount = amount,
            result = result,
            date = date
        )
    }
}
