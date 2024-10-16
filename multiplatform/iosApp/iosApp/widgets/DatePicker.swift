import SwiftUI

func openCalendar(
    onValueChanged: @escaping (Date) -> Void,
    onCancelled: @escaping () -> Void = {}
) {
    let calendar = Calendar.current
    let currentYear = calendar.component(.year, from: Date())
    let currentMonth = calendar.component(.month, from: Date())
    let currentDay = calendar.component(.day, from: Date())
    let currentHour = calendar.component(.hour, from: Date())
    let currentMinute = calendar.component(.minute, from: Date())

    let dialog = DatePicker(
        "Select a date",
        selection: .constant(
            calendar.date(
                from: DateComponents(
                    year: currentYear,
                    month: currentMonth,
                    day: currentDay,
                    hour: currentHour,
                    minute: currentMinute
                )
            ) ?? Date()
        ), displayedComponents: [.date, .hourAndMinute])
        dialog.datePickerStyle(.wheel)
//        dialog.onChange(of: dialog.selection) { date in
//            onValueChanged(date)
//            onCancelled()
//        }
        dialog.onDisappear {
            onCancelled()
        }
}
