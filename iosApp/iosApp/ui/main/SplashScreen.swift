// import shared_frontend
// import SwiftUI

//struct SplashScreen: View {
//    private let navigate: (NavigationDirections) -> Void
//    private let navigateBack: () -> Void
//    
//    private let viewModel: MainViewModel = MainDiHelper().getMainViewModel()
//    @ObservedObject private var state: ObservableState<MainState>
//
//    init(
//        navigate: @escaping (NavigationDirections) -> Void,
//        navigateBack: @escaping () -> Void
//    ) {
//        // swiftlint:disable force_cast
//        self.state = ObservableState<MainState>(value: viewModel.viewState.value as! MainState)
//        // swiftlint:enable force_cast
//        self.navigate = navigate
//        self.navigateBack = navigateBack
//        observeState()
//    }
//    
//    private func observeState() {
////        viewModel.viewState.collect(collector: Collector<MainState> { state in
////            self.state.value = state
////            if (!state.isLoading) {
////                navigate(state.directions)
////            }
////        }) { _ in
////            print("Error ocurred during state collection")
////        }
//    }
//    
//    private func onCommandReceived(command: NavigationDirections) {
//        print(command.destination)
//        if (command is NavigationDirections.NavigateUp) {
//            navigateBack()
//        } else {
//            navigate(command)
//        }
//     }
//    
//    var body: some View {
//            VStack(alignment: .leading) {
//                Color.black.ignoresSafeArea()
////                Image()
////                    .resizable()
////                    .scaledToFit()
////                    .frame(width: 300, height: 300)
//        }
//    }
//}

//struct SplashScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        SplashScreen(navigate: { _ in  }, navigateBack: {})
//    }
//}
