package pl.msiwak.multiplatform.commonObject

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ResultData(
    val id: String = "",
    val exerciseId: String = "",
    val result: String,
    val amount: String,
    val date: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)