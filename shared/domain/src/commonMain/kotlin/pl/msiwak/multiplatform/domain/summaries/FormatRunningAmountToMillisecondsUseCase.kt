package pl.msiwak.multiplatform.domain.summaries

interface FormatRunningAmountToMillisecondsUseCase {
    operator fun invoke(amount: String): Int
}
