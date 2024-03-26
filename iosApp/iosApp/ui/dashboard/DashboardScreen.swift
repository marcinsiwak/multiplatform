import shared
import SwiftUI

struct DashboardScreen: View {
    let viewModel = DashboardDiHelper().getDashboardViewModel()
    @ObservedObject private var state: ObservableState<DashboardState>

    init() {
        // swiftlint:disable force_cast
        self.state = ObservableState<DashboardState>(value: viewModel.viewState.value as! DashboardState)
        // swiftlint:enable force_cast

        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<DashboardState>{
            state in self.state.value = state

        }) { _ in
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
                        .foregroundColor(.black)
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
                        .foregroundColor(.black)
                }
            }
            .accentColor(.black)

        }
    }
}

struct DashboardScreen_Previews: PreviewProvider {
    static var previews: some View {
        DashboardScreen()
    }
}
