import shared
import SwiftUI

struct DashboardScreen: View {
    let viewModel = DashboardDiHelper().getDashboardViewModel()
    @ObservedObject private var state: ObservableState<DashboardState>

    init() {
        self.state = ObservableState<DashboardState>(value: viewModel.viewState.value as! DashboardState)
        
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<DashboardState>{
            state in self.state.value = state
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    var body: some View {
                    
        ZStack {
            TabView {
                ZStack {
                    SummaryScreen()
                    if(state.value.isOfflineBannerVisible) {
                        OfflineBanner(
                            onSignInUpClicked: {
                                viewModel.onSignInUpClicked()
                            }
                        )
                    }
                }.tabItem {
                    //                Image(systemName: "phone.fill")
                    Text("Summary")
                        .foregroundColor(.onPrimary)
                }
                
                //            Text("Account")
                //                .tabItem {
                //                    //                Image(systemName: "tv.fill")
                //                    Text("Account")
                //                }
                ZStack {
                    SettingsScreen()
                    if(state.value.isOfflineBannerVisible) {
                        OfflineBanner(
                            onSignInUpClicked: {
                                viewModel.onSignInUpClicked()
                            }
                        )
                    }
                } .tabItem {
                    //                Image(systemName: "tv.fill")
                    Text("Settings")
                        .foregroundColor(.onPrimary)
                }
            }
            .accentColor(.onPrimary)

        }
    }
}

struct DashboardScreen_Previews: PreviewProvider {
    static var previews: some View {
        DashboardScreen()
    }
}
