package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ResultData

data class Summary(
    val id: Long = 0,
    val exerciseType: String,
    val results: List<ResultData>
)