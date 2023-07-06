import SwiftUI
import shared

struct SettingsScreen: View {
    
    let viewModel = SettingsDiHelper().getViewModel()
    
    @ObservedObject private var state: ObservableState<SettingsState>

    init() {
        self.state = ObservableState<SettingsState>(value: viewModel.viewState.value as! SettingsState)
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<SettingsState>{ state in
            self.state.value = state
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("")
                .font(.largeTitle)
                .foregroundColor(.white)
                .padding()
            
            SettingsItem(
                title: "",
                onItemClicked: {
                    viewModel.onLogoutClicked()
                }
            )
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .padding(.horizontal, Dimensions.space_8)
        .background(.black)
    }
}

struct SettingsScreen_Previews: PreviewProvider {
    static var previews: some View {
        SettingsScreen()
    }
}
