package pl.msiwak.multiplatform.ui.exercise

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.data.common.ResultData

data class ExerciseState(
    val exerciseTitle: String = "",
    val newResult: String = "",
    val newResultDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val results: List<ResultData> = listOf(),
)