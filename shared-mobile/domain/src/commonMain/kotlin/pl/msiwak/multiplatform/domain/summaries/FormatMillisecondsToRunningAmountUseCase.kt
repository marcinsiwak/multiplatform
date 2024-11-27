package pl.msiwak.multiplatform.domain.summaries

interface FormatMillisecondsToRunningAmountUseCase {
    operator fun invoke(amount: String): String
}
