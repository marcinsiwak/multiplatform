
import SwiftUI

struct DashboardScreen: View {
    var body: some View {
        
        TabView {
            SummaryScreen()
                .tabItem {
                    //                Image(systemName: "phone.fill")
                    Text("Summaryyyy")
                }
            Text("Account")
                .tabItem {
                    //                Image(systemName: "tv.fill")
                    Text("Account")
                }
            Text("Settings")
                .tabItem {
                    //                Image(systemName: "tv.fill")
                    Text("Settings")
                }
        }
    }
}

struct DashboardScreen_Previews: PreviewProvider {
    static var previews: some View {
        DashboardScreen()
    }
}
