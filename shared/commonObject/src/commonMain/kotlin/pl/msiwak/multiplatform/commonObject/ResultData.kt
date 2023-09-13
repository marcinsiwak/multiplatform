package pl.msiwak.multiplatform.commonObject

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@kotlinx.serialization.Serializable
data class ResultData(
    val result: Double,
    val amount: String,
    val date: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)