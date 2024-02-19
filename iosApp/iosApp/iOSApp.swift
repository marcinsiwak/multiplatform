import shared
import SwiftUI

@main
// swiftlint:disable type_name
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ComposeView()
//            ContentView()
//                .onAppear {
//                    UINavigationBar.appearance().backIndicatorImage = UIImage(systemName: "arrow.backward")
//                    UINavigationBar.appearance().backIndicatorTransitionMaskImage = UIImage(systemName: "arrow.backward")
//                }
        }
    }
}
// swiftlint:enable type_name

extension UINavigationController {

  open override func viewWillLayoutSubviews() {
    super.viewWillLayoutSubviews()
    navigationBar.topItem?.backButtonDisplayMode = .minimal
  }
}
