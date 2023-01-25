import SwiftUI
import shared

struct WelcomeScreen: View {
    let diHelper = DiHelper()
    private let navigateToRegistration: () -> Void
    private let navigateToLogin: () -> Void
    private let viewModel: MainViewModel

    
    init(viewModel: MainViewModel, navigateToRegistration: @escaping () -> Void, navigateToLogin: @escaping () -> Void) {
        self.viewModel = viewModel
        self.navigateToRegistration = navigateToRegistration
        self.navigateToLogin = navigateToLogin
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
        
     }
    
    var body: some View {
            VStack(alignment: .leading) {
            
                    Button("Register") {
                        diHelper.onRegistrationClicked()
                        
                    }.padding(30)
                
                    Button("Login") {
                        diHelper.onLoginClicked()

                    }.padding(30)

        }
    }
//    struct WelcomeScreen_Previews: PreviewProvider {
//        static var previews: some View {
//            WelcomeScreen()
//        }
//    }
}

