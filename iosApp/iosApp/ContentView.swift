import SwiftUI
import shared

private let RegistrationRoute = "registration"
private let LoginRoute = "login"


struct ContentView: View {
    @State private var route = [String]()

    let diHelper = DiHelper()
    
    var body: some View {
        NavigationStack(path: $route) {
            WelcomeScreen(
                viewModel: diHelper.getMainViewModel(),
                navigateToRegistration: { route.append(RegistrationRoute) },
                navigateToLogin: { route.append(LoginRoute) }
            ).navigationDestination(for: String.self) { destination in
                switch (destination) {
                    case RegistrationRoute:
                        RegisterScreen()
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

