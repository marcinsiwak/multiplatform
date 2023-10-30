package pl.msiwak.multiplatform.domain.summaries

class FormatRunningAmountToMillisecondsUseCase {
    operator fun invoke(params: Params): String {
        val hoursMillis = params.hours.toInt() * 3600000
        val minutesMillis = params.minutes.toInt() * 60000
        val secondsMillis = params.seconds.toInt() * 1000
        val milliseconds = params.milliseconds.toInt()
        return (hoursMillis + minutesMillis + secondsMillis + milliseconds).toString()
    }

    data class Params(
        val hours: String,
        val minutes: String,
        val seconds: String,
        val milliseconds: String
    )
}