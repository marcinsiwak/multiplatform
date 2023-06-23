import SwiftUI
import shared

struct ResultsTableView: View {
    let resultDataTitles: [String?]
    let unit: String
    let results: [FormattedResultData]
    let exerciseType: ExerciseType
    let sortType: SortType?
    let isNewResultEnabled: Bool
    let newResultData: FormattedResultData
    @ObservedObject var focusedFieldPos: ObservableEvent<Int32?>
    let onAddNewResultClicked: () -> Void
    let onLabelClicked: (Int) -> Void
    let onResultValueChanged: (String) -> Void
    let onAmountValueChanged: (String) -> Void
    let onDatePicked: (Kotlinx_datetimeLocalDateTime) -> Void
    let onDateClicked: () -> Void
    let onResultLongClick: (Int) -> Void

    
    var body: some View {
        
        
        VStack {
            // Header
            HStack(
                spacing: Dimensions.space_24
            ) {
                TextWithDrawableView(
                    text: resultDataTitles[safe: 0] ?? "",
                    iconResId: sortType == .resultIncreasing ? "chevron.up" :
                            sortType == .resultDecreasing ? "chevron.down" : nil,
                    color: Color.white
                )
                    .padding(.horizontal, Dimensions.space_16)
                    .foregroundColor(.white)
                    .onTapGesture {
                        onLabelClicked(0)
                    }
                TextWithDrawableView(
                    text: resultDataTitles[safe: 1] ?? "",
                    iconResId: sortType == .amountIncreasing ? "chevron.up" :
                        sortType == .amountDecreasing ? "chevron.down" : nil,
                    color: Color.white
                )
                    .padding(.horizontal, Dimensions.space_16)
                    .foregroundColor(.white)
                    .onTapGesture {
                        onLabelClicked(1)
                    }
                TextWithDrawableView(
                    text: resultDataTitles[safe: 2] ?? "",
                    iconResId: sortType == .dateIncreasing ? "chevron.up" :
                        sortType == .dateDecreasing ? "chevron.down" : nil,
                    color: Color.white
                )
                    .padding(.horizontal, Dimensions.space_16)
                    .foregroundColor(.white)
                    .onTapGesture {
                        onLabelClicked(2)
                    }
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, Dimensions.space_8)
            .background(Color(MR.colors().gray.getUIColor()))

            // Results
            List {
                if isNewResultEnabled {
                    NewResultView(
                        focusedFieldPos: focusedFieldPos,
                        newResultData: newResultData,
                        exerciseType: exerciseType,
                        onResultValueChanged: { onResultValueChanged($0) },
                        onAmountValueChanged: { onAmountValueChanged($0) },
                        onDatePicked: { onDatePicked($0) },
                        onDateClicked: onDateClicked
                    )
                    .listRowBackground(Color.black)
                }
                
                if results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text("Add first result")
                    }
                    .frame(maxWidth: .infinity, minHeight: Dimensions.results_min_height)
                    .listRowBackground(Color.black)
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
                .listRowBackground(Color.black)

                if !results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text("Add new result")
                    }
                    .frame(maxWidth: .infinity)
                    .listRowBackground(Color.black)
                }
            }
            .listStyle(PlainListStyle())
            .background(.black)
            .scrollContentBackground(.hidden)
        }
    }
}

//struct ResultTableView_Previews: PreviewProvider {
//    static var previews: some View {
//        ResultTableView()
//    }
//}
