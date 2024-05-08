import shared
import SwiftUI

struct ResultsTableView: View {
    let resultDataTitles: [ResultTableItemData?]
    let unit: String
    let results: [FormattedResultData]
    let exerciseType: ExerciseType
    let isNewResultEnabled: Bool
    let newResultData: FormattedResultData
    @ObservedObject var focusedFieldPos: ObservableEvent<Int32?>
    let onAddNewResultClicked: () -> Void
    let onLabelClicked: (Int) -> Void
    let onResultValueChanged: (String) -> Void
    let onAmountValueChanged: (String) -> Void
    let onDatePicked: (Kotlinx_datetimeLocalDateTime) -> Void
    let onDateClicked: () -> Void
    let onAmountClicked: () -> Void
    let onResultLongClick: (Int) -> Void

    
    var body: some View {
        
        
        VStack {
            // Header
            HStack(
                spacing: Dimensions.space_24
            ) {
                
                ForEach(Array(resultDataTitles.enumerated()), id: \.1) { index, item in
                    TextWithDrawableView(
                        text: item?.text ?? "",
                        iconResId: item?.isArrowUp == true ? "chevron.up" :
                            item?.isArrowUp == false ? "chevron.down" : nil,
                        color: Color.onPrimary
                    )
                        .padding(.horizontal, Dimensions.space_16)
                        .foregroundColor(.onPrimary)
                        .onTapGesture {
                            onLabelClicked(index)
                        }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, Dimensions.space_8)
            .background(Color.colorPrimary)

            // Results
            ScrollView {
                if isNewResultEnabled {
                    NewResultView(
                        focusedFieldPos: focusedFieldPos,
                        newResultData: newResultData,
                        exerciseType: exerciseType,
                        onResultValueChanged: { onResultValueChanged($0) },
                        onAmountValueChanged: { onAmountValueChanged($0) },
                        onDatePicked: { onDatePicked($0) },
                        onDateClicked: onDateClicked,
                        onAmountClicked: onAmountClicked
                    )
                    .listRowSeparator(.hidden)
                    
                    Divider()
                        .background(.tertiary)
                }
                
                if results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text(Res.string().add_first_result.desc().localized())
                    }
                    .frame(maxWidth: .infinity, minHeight: Dimensions.results_min_height)
                    .listRowSeparator(.hidden)
                }
                
                ForEach(results.indices, id: \.self) { index in
                    let item = results[index]
                    ResultView(
                        result: item.result,
                        amount: item.amount,
                        date: item.date,
                        onResultLongClick: { onResultLongClick(index) }
                    )
                    .listRowSeparator(.hidden)
                    
                    Divider()
                        .background(.tertiary)
                }
                

                if !results.isEmpty && !isNewResultEnabled {
                    Button(action: onAddNewResultClicked) {
                        Text(Res.string().add_new_result.desc().localized())
                    }
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, 16)
                    .listRowSeparator(.hidden)
                }
            }
            .listRowSeparator(.hidden)
            .listStyle(PlainListStyle())
            .background(Color.colorPrimary)
            .scrollContentBackground(.hidden)
        }
    }
}

//struct ResultTableView_Previews: PreviewProvider {
//    static var previews: some View {
//        ResultsTableView()
//    }
//}

extension ResultTableItemData: Identifiable {}
