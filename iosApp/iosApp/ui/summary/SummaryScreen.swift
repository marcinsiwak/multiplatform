import SwiftUI
import shared

struct SummaryScreen: View {
    let viewModel = SummaryDiHelper().getSummaryViewModel()
    @ObservedObject private var state: ObservableState<SummaryState>
    


    init() {
        self.state = ObservableState<SummaryState>(value: viewModel.viewState.value as! SummaryState)
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<SummaryState>{
            state in onStateReceived(state: state)
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ScrollView {
                ForEach(state.value.categories) { item in
                    CategoryItem(categoryData: item)
                        .onTapGesture {
                            viewModel.onCategoryClicked(id: item.id)
                        }
                        .padding(.top, Dimensions.space_16)
                }
                
                Button(action: {
                    viewModel.onAddCategoryClicked()
                }, label: {
                    HStack {
                        Image(systemName: "plus.circle")
                            .tint(.gray)
                        Text("MR.strings().summary_add_category.desc().localized()")
                            .foregroundColor(.gray)
                    }
                    .padding(.vertical, Dimensions.space_24)
                    .frame(maxWidth: .infinity, alignment: .center)
                })
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .padding(.horizontal, Dimensions.space_8)
        .background(.black)
    
    }

    private func onStateReceived(state: SummaryState) {
        self.state.value = state
     }
    
}
extension CategoryData: Identifiable {}

//struct SummaryScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        SummaryScreen()
//    }
//}
