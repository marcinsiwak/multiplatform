package pl.msiwak.multiplatform.domain.summaries

class FormatMillisecondsToRunningAmountUseCase {
    operator fun invoke(amount: String): String {
        val amountInt = amount.toInt()
        val totalSeconds = amountInt / 1000
        val hours = totalSeconds / 3600
        val remainingSeconds = totalSeconds % 3600
        val minutes = remainingSeconds / 60
        val seconds = remainingSeconds % 60
        val milliseconds = amountInt % 1000
        return "$hours:$minutes:$seconds.$milliseconds"
    }
}