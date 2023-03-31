import SwiftUI
import shared

struct ResultsTableView: View {
    let results: [FormattedResultData]
    let isNewResultEnabled: Bool
    let newResultData: FormattedResultData
    let onAddNewResultClicked: () -> Void
    let onResultValueChanged: (String) -> Void
    let onAmountValueChanged: (String) -> Void
    let onDateValueChanged: (String) -> Void
    let onDateClicked: () -> Void
    let onResultLongClick: (Int) -> Void
    
    var body: some View {
        VStack {
            // Header
            HStack {
                Text("Weight")
                    .padding(.horizontal, 16)
                    .foregroundColor(.white)
                Text("Reps")
                    .padding(.horizontal, 16)
                    .foregroundColor(.white)
                Text("Date")
                    .padding(.horizontal, 16)
                    .foregroundColor(.white)
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, 8)
            .background(Color.blue)
            
            // Results
            List {
                if isNewResultEnabled {
                    NewResultView(
                        newResultData: newResultData,
                        onResultValueChanged: { onResultValueChanged($0) },
                        onAmountValueChanged: { onAmountValueChanged($0) },
                        onDateValueChanged: { onDateValueChanged($0) },
                        onDateClicked: onDateClicked
                    )
                }
                
                if results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text("Add first result")
                    }
                    .frame(maxWidth: .infinity, minHeight: 200)
                }
                
                ForEach(results.indices, id: \.self) { index in
                    let item = results[index]
                    ResultView(
                        result: item.result,
                        amount: item.amount,
                        date: item.date,
                        onResultLongClick: { onResultLongClick(index) }
                    )
                }
                
                if !results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text("Add new result")
                    }
                    .frame(maxWidth: .infinity)
                }
            }
        }
    }
}

//struct ResultTableView_Previews: PreviewProvider {
//    static var previews: some View {
//        ResultTableView()
//    }
//}
