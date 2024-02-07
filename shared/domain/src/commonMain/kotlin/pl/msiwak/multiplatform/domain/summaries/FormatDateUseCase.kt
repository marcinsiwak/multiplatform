package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime

interface FormatDateUseCase {
    operator fun invoke(date: LocalDateTime): String
}
