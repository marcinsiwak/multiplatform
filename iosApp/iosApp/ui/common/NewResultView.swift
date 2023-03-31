import SwiftUI
import shared

struct NewResultView: View {
    @State private var result = ""
    @State private var amount = ""
    @State private var date = ""
    
    var newResultData: FormattedResultData
    var onResultValueChanged: (String) -> Void
    var onAmountValueChanged: (String) -> Void
    var onDateValueChanged: (String) -> Void
    var onDateClicked: () -> Void
    
    var body: some View {
        HStack {
            InputView(value: $result, trailingIcon: {}, onValueChange: { text in
                onResultValueChanged(text)
            })
                .frame(maxWidth: .infinity)
                .padding(4)
            InputView(value: $amount, trailingIcon: {}, onValueChange: onAmountValueChanged)
                .frame(maxWidth: .infinity)
                .padding(4)
            InputView(value: $date, trailingIcon: {
                Image(systemName: "calendar")
                    .onTapGesture {
                        onDateClicked()
                    }
            }
                      ,onValueChange: onDateValueChanged
            )
                .frame(maxWidth: .infinity)
                .padding(4)
        }
    }
}


//struct NewResultView_Previews: PreviewProvider {
//    static var previews: some View {
//        NewResultView()
//    }
//}
