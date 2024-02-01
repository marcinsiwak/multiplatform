import shared
import SwiftUI
import Foundation


struct NewResultView: View {
    
    @State private var selectedDate = Date.now
    
    @State private var result: String = ""
    @State private var amount: String = ""

    @FocusState private var isResultFieldFocused: Bool
    @FocusState private var isAmountFieldFocused: Bool
    
    @ObservedObject var focusedFieldPos: ObservableEvent<Int32?>
    var newResultData: FormattedResultData
    var exerciseType: ExerciseType
    var onResultValueChanged: (String) -> Void
    var onAmountValueChanged: (String) -> Void
    var onDatePicked: (Kotlinx_datetimeLocalDateTime) -> Void
    var onDateClicked: () -> Void
    var onAmountClicked: () -> Void

    init(
        focusedFieldPos: ObservableEvent<Int32?>,
        newResultData: FormattedResultData,
        exerciseType: ExerciseType,
        onResultValueChanged: @escaping (String) -> Void,
        onAmountValueChanged: @escaping (String) -> Void,
        onDatePicked: @escaping (Kotlinx_datetimeLocalDateTime) -> Void,
        onDateClicked: @escaping () -> Void,
        onAmountClicked: @escaping () -> Void
    ) {
        self.focusedFieldPos = focusedFieldPos
        self.newResultData = newResultData
        self.exerciseType = exerciseType
        self.onResultValueChanged = onResultValueChanged
        self.onAmountValueChanged = onAmountValueChanged
        self.onDatePicked = onDatePicked
        self.onDateClicked = onDateClicked
        self.onAmountClicked = onAmountClicked
        self._amount = State(initialValue: newResultData.amount)
        self._result = State(initialValue: newResultData.result)
        print("STATE22:", amount)

    }
    
    var body: some View {
        
        HStack(
            spacing: Dimensions.space_24
        ) {
            ResultInputView(
                initValue: newResultData.result,
                hintText: "100",
                isError: newResultData.isResultError,
                onValueChange: { text in onResultValueChanged(text)},
                hasFocus: isResultFieldFocused
            )
            .frame(width: Dimensions.result_input_view_width)
            .keyboardType(.decimalPad)
            .padding(.leading, 24)
            
            
            ResultInputView(
                initValue: newResultData.amount,
                hintText: exerciseType == .gym ? "10" : "00:00:00.000" ,
                isError: newResultData.isAmountError,
                onValueChange: onAmountValueChanged,
                hasFocus: isAmountFieldFocused
            )
            .frame(width: 116)
            .keyboardType(.decimalPad)
            .padding(.leading, 16)
            .onTapGesture {
                if (exerciseType == ExerciseType.running) {
                    onAmountClicked()
                }
            }
            
            DatePicker("", selection: $selectedDate, displayedComponents: .date)
//                .labelsHidden()
                .environment(\.locale, Locale(identifier: "pl_PL"))
                .colorMultiply(.white)
                .colorScheme(.dark)
                .onChange(of: selectedDate, perform: { value in
                    let newValue = DateConverterKt.toKotlinDateTime(date: value)
                    onDatePicked(newValue)
                })
                .fixedSize()
        }
        .background(.black)
        .onDisappear{
            result = ""
            amount = ""
        }
        .onChange(of: focusedFieldPos.value) { newValue in
                    isResultFieldFocused = newValue == 1
                    isAmountFieldFocused = newValue == 2
                }
    }
}

//struct NewResultView_Previews: PreviewProvider {
//    static var previews: some View {
//        NewResultView()
//    }
//}
