import SwiftUI
import shared

struct SummaryScreen: View {
    private let viewModel = SummaryDiHelper().getSummaryViewModel()
    @ObservedObject private var state: ObservableSummaryState
    

    init() {
        self.state = viewModel.observableState()
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
            ForEach(state.value.categories) { item in
                CategoryItem(categoryData: item)
                    .onTapGesture {
                        viewModel.onExerciseClicked(id: item.id)
                    }
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .background(.gray)

    }

    private func onStateReceived(state: SummaryState) {
        self.state.value = state
     }
    
}
extension CategoryData: Identifiable {}

extension SummaryViewModel {
    func observableState() -> ObservableSummaryState {
        return (viewState.value as! SummaryState).wrapAsObservable()
    }
}

//struct SummaryScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        SummaryScreen()
//    }
//}
