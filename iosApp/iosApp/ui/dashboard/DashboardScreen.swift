
import SwiftUI

struct DashboardScreen: View {
    
    init() {
        
        
    }
    
    var body: some View {
        
        TabView {
            SummaryScreen()
                .tabItem {
                    //                Image(systemName: "phone.fill")
                    Text("Summary")
                        .foregroundColor(.white)
                }
//            Text("Account")
//                .tabItem {
//                    //                Image(systemName: "tv.fill")
//                    Text("Account")
//                }
            Text("Settings")
                .tabItem {
                    //                Image(systemName: "tv.fill")
                    Text("Settings")
                        .foregroundColor(.white)
                }
        }
        .accentColor(.white)
    
    }
}

struct DashboardScreen_Previews: PreviewProvider {
    static var previews: some View {
        DashboardScreen()
    }
}