package pl.msiwak.multiplatform.domain.summaries

class FormatRunningAmountToMillisecondsUseCase {
    operator fun invoke(amount: String): Int {
        val hoursMillis: Int
        val minutesMillis: Int
        val secondsMillis: Int
        val milliseconds: Int
        when (amount.length) {
            12 -> {
                hoursMillis = amount.substring(0, 2).toInt() * 3600000
                minutesMillis = amount.substring(3, 5).toInt() * 60000
                secondsMillis = amount.substring(6, 8).toInt() * 1000
                milliseconds = amount.substring(9, amount.length).toInt()
            }

            9 -> {
                hoursMillis = 0
                minutesMillis = amount.substring(0, 2).toInt() * 60000
                secondsMillis = amount.substring(3, 5).toInt() * 1000
                milliseconds = amount.substring(6, amount.length).toInt()
            }

            else -> {
                hoursMillis = 0
                minutesMillis = 0
                secondsMillis = amount.substring(0, 2).toInt() * 1000
                milliseconds = amount.substring(3, amount.length).toInt()
            }
        }

        return hoursMillis + minutesMillis + secondsMillis + milliseconds
    }
}
