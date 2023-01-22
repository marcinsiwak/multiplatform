import SwiftUI
import shared
import Firebase



@main
struct iOSApp: App {
    
    init() {
        HelperKt.doInitKoin()
        FirebaseApp.configure()
       }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
