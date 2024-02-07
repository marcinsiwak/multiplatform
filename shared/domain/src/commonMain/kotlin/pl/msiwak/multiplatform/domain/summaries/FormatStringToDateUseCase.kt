package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime

interface FormatStringToDateUseCase {
    operator fun invoke(date: String): LocalDateTime
}
