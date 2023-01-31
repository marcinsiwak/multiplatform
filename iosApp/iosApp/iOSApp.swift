import SwiftUI
import shared
import Firebase



@main
struct iOSApp: App {
        
    init() {
        HelperKt.doInitKoin()
        HelperKt.doInitNapier()
        FirebaseApp.configure()
       }
    
	var body: some Scene {
		WindowGroup {
            ContentView()
		}
	}
}
