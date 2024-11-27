package pl.msiwak.multiplatform.domain.summaries

interface FormatRunningAmountUseCase {
    operator fun invoke(params: Params): String

    data class Params(
        val hours: String,
        val minutes: String,
        val seconds: String,
        val milliseconds: String
    )
}
