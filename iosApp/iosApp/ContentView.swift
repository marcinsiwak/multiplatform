import SwiftUI
import shared

private let RegistrationRoute = "registration"
private let LoginRoute = "login"
private let DashboardRoute = "dashboard"
private let CategoryRoute = "category"


struct ContentView: View {
    @State private var route = [String]()

    let mainDiHelper = MainDiHelper()
    
    
    var body: some View {
        NavigationStack(path: $route) {
            WelcomeScreen(
                viewModel: mainDiHelper.getMainViewModel(),
                navigateToRegistration: { route.append(RegistrationRoute) },
                navigateToLogin: { route.append(LoginRoute) },
                navigateToDashboard: { route.append(DashboardRoute)},
                navigateToCategory: { route.append(CategoryRoute)}
            ).navigationDestination(for: String.self) { destination in
                switch (destination) {
                    case RegistrationRoute:
                        RegisterScreen()
                    case DashboardRoute:
                        DashboardScreen().navigationBarBackButtonHidden(true)
                    default:
                        Text("None")
                }
            }
        }
    }
}



//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}

