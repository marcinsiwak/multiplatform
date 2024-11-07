import shared
import SwiftUI

@main
struct IOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            MainView()
                .ignoresSafeArea(edges: .all)
                .ignoresSafeArea(.keyboard)
        }
    }
}
