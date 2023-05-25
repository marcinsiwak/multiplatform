import SwiftUI
import shared


struct NewResultView: View {
    @State private var result = ""
    @State private var amount = ""
    @State private var date = ""
    @State private var selectedDate = Date.now
    @State private var isDatePickerVisible = false

    var newResultData: FormattedResultData
    var onResultValueChanged: (String) -> Void
    var onAmountValueChanged: (String) -> Void
    var onDatePicked: (Kotlinx_datetimeLocalDateTime) -> Void
    var onDateClicked: () -> Void
    
    var body: some View {
        HStack {
            ResultInputView(value: $result, isError: newResultData.isResultError, onValueChange: { text in
                onResultValueChanged(text)
            })
                .frame(maxWidth: .infinity)
                .padding(4)
            ResultInputView(value: $amount, isError: newResultData.isAmountError, onValueChange: onAmountValueChanged)
                .frame(maxWidth: .infinity)
                .padding(4)
            
            DatePicker("", selection: $selectedDate, displayedComponents: .date)
                .labelsHidden()
                .colorMultiply(.white)
                .colorScheme(.dark)
                .onChange(of: selectedDate, perform: { value in
                    let newValue = DateConverterKt.toKotlinDateTime(date: value)
                    let month = newValue.dayOfMonth
                    isDatePickerVisible = false
                    onDatePicked(newValue)
                })
        }
        .background(.black)
    }
}


//struct NewResultView_Previews: PreviewProvider {
//    static var previews: some View {
//        NewResultView()
//    }
//}
