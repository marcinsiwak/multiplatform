package pl.msiwak.multiplatform.android.ui.widgets

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar
import java.util.Date
import kotlinx.datetime.LocalDateTime

fun openCalendar(
    context: Context,
    onValueChanged: (LocalDateTime) -> Unit,
    onCancelled: () -> Unit = {}
) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = calendar.get(Calendar.MINUTE)
    val currentSecond = calendar.get(Calendar.SECOND)

    val dialog = DatePickerDialog(context, { _, year, month, day ->
        onValueChanged(
            LocalDateTime(
                year,
                month + 1,
                day,
                currentHour,
                currentMinute,
                currentSecond
            )
        )
        onCancelled()
    }, currentYear, currentMonth, currentDay)
    dialog.datePicker.maxDate = Date().time
    dialog.setOnCancelListener {
        onCancelled()
    }
    dialog.show()
}
