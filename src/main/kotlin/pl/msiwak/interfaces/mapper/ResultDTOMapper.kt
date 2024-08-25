package pl.msiwak.interfaces.mapper

import pl.msiwak.domain.entities.ResultEntity
import pl.msiwak.interfaces.dtos.ResultDTO

class ResultDTOMapper : Mapper<ResultEntity, ResultDTO>() {
    override fun map(value: ResultEntity): ResultDTO = with(value) {
        ResultDTO(
            id = id,
            exerciseId = id!!,
            amount = amount,
            result = result,
            date = date
        )
    }
}
