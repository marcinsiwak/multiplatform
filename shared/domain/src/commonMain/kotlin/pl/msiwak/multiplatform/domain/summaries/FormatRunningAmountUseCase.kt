package pl.msiwak.multiplatform.domain.summaries

class FormatRunningAmountUseCase {
    operator fun invoke(params: Params): String {
        return with(params) {
            var finalTime = ""
            val hour = formatOneDigit(hours)
            val minute = formatOneDigit(minutes)
            val second = formatOneDigit(seconds)
            val millisecond = formatMillisecondsOneDigit(milliseconds)

            if (hour != "00") {
                finalTime = finalTime.plus("$hour:")
            }
            if (hour != "00" && minute == "00") {
                finalTime = finalTime.plus("$minute:")
            } else if (hour != "00" && minute != "00") {
                finalTime = finalTime.plus("$minute:")
            } else if (hour == "00" && minute != "00") {
                finalTime = finalTime.plus("$minute:")
            }
            finalTime = finalTime.plus("$second.")
            finalTime = finalTime.plus(millisecond)

            finalTime
        }
    }

    private fun formatOneDigit(input: String): String {
        val newInput = input.ifEmpty { "0" }
        return if (newInput.length == 1) {
            "0$newInput"
        } else {
            newInput
        }
    }

    private fun formatMillisecondsOneDigit(input: String): String {
        return when (input.length) {
            1 -> "00$input"
            2 -> "0$input"
            else -> input
        }
    }

    data class Params(
        val hours: String,
        val minutes: String,
        val seconds: String,
        val milliseconds: String
    )
}