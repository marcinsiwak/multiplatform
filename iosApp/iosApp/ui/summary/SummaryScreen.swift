import shared
import SwiftUI

struct SummaryScreen: View {
    let viewModel = SummaryDiHelper().getSummaryViewModel()
    @ObservedObject private var state: ObservableState<SummaryState>

    init() {
        self.state = ObservableState<SummaryState>(value: viewModel.viewState.value as! SummaryState)
        
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<SummaryState> { state in
            onStateReceived(state: state)
        }) { _ in
            print("Error ocurred during state collection")
        }
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            BannerView()
            ScrollView {
                ForEach(state.value.categories) { item in
                    CategoryItem(category: item)
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
                            .tint(Color.colorTertiary)
                        Text(MR.strings().summary_add_category.desc().localized())
                            .foregroundColor(Color.colorTertiary)
                    }
                    .padding(.vertical, Dimensions.space_24)
                    .frame(maxWidth: .infinity, alignment: .center)
                })
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .padding(.horizontal, Dimensions.space_8)
        .background(Color.colorPrimary)
        .onAppear {
            viewModel.onResume()
        }
    }

    private func onStateReceived(state: SummaryState) {
        self.state.value = state
    }
}

extension Category: Identifiable {}

struct SummaryScreen_Previews: PreviewProvider {
    static var previews: some View {
        SummaryScreen()
    }
}
