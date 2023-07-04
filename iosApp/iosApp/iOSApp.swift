import SwiftUI
import shared
import Firebase



@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

        
    init() {
        HelperKt.doInitKoin()
        HelperKt.doInitNapier()
       }
    
	var body: some Scene {
		WindowGroup {
            ContentView()
                .onAppear {
                    UINavigationBar.appearance().backIndicatorImage = UIImage(systemName: "arrow.backward")
                    UINavigationBar.appearance().backIndicatorTransitionMaskImage = UIImage(systemName: "arrow.backward")
                }
		}
	}
    
}

extension UINavigationController {

  open override func viewWillLayoutSubviews() {
    super.viewWillLayoutSubviews()
    navigationBar.topItem?.backButtonDisplayMode = .minimal
  }

}
