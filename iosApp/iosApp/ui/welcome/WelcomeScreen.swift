import SwiftUI
import shared

struct WelcomeScreen: View {
    let welcomeDiHelper = WelcomeDiHelper()
    private let navigateToRegistration: () -> Void
    private let navigateToLogin: () -> Void
    private let navigateToDashboard: () -> Void
    private let navigateToCategory: () -> Void
    private let viewModel: MainViewModel

    
    init(
        viewModel: MainViewModel,
        navigateToRegistration: @escaping () -> Void,
        navigateToLogin: @escaping () -> Void,
        navigateToDashboard: @escaping () -> Void,
        navigateToCategory: @escaping () -> Void
    ) {
        self.viewModel = viewModel
        self.navigateToRegistration = navigateToRegistration
        self.navigateToLogin = navigateToLogin
        self.navigateToDashboard = navigateToDashboard
        self.navigateToCategory = navigateToCategory
        observeState()
    }
    
    private func observeState() {
        viewModel.mainNavigator.commands.collect(collector: Collector<NavigationCommand>{
            command in onCommandReceived(command: command)
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    private func onCommandReceived(command: NavigationCommand) {
        print(command.destination)
        if(command.destination == "registration") {
            navigateToRegistration()
        }
        
        if(command.destination == "login") {
            navigateToLogin()
        }
        
        if(command.destination == "dashboard") {
            navigateToDashboard()
        }
        
        if(command.destination == "category") {
            (command.destination as? Category)
//            navigateToCategory(command)
        }
        
     }
    
    var body: some View {
            VStack(alignment: .leading) {
            
                    Button("Register") {
                        welcomeDiHelper.onRegistrationClicked()
                        
                    }.padding(30)
                
                    Button("Login") {
//                        welcomeDiHelper.onLoginClicked()
                        navigateToDashboard()
                    }.padding(30)

        }
    }
//    struct WelcomeScreen_Previews: PreviewProvider {
//        static var previews: some View {
//            WelcomeScreen()
//        }
//    }
}

