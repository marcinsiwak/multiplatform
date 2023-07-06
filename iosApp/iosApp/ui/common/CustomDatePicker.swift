import SwiftUI

struct CustomDatePicker: UIViewRepresentable {
    @Binding var selectedDate: Date
    
    class Coordinator: NSObject {
        var parent: CustomDatePicker
        
        init(_ parent: CustomDatePicker) {
            self.parent = parent
        }
        
        @objc func dateChanged(_ sender: UIDatePicker) {
            parent.selectedDate = sender.date
        }
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIView(context: Context) -> UIDatePicker {
        let datePicker = UIDatePicker()
        datePicker.datePickerMode = .date
        datePicker.addTarget(context.coordinator, action: #selector(Coordinator.dateChanged(_:)), for: .valueChanged)
        datePicker.setValue(UIColor.red, forKeyPath: "textColor") // Customize the text color
        datePicker.setValue(false, forKeyPath: "highlightsToday") // Disable highlighting of today's date
        
        return datePicker
    }
    
    func updateUIView(_ uiView: UIDatePicker, context: Context) {
        uiView.date = selectedDate
    }
}
