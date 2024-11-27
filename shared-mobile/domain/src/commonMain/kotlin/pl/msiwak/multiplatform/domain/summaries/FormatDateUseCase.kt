package pl.msiwak.multiplatform.domain.summaries

interface FormatDateUseCase {
    operator fun invoke(date: Long): String
}
