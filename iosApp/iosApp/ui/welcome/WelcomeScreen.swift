import SwiftUI
import shared

struct WelcomeScreen: View {
    let welcomeDiHelper = WelcomeDiHelper()
    private let navigateToRegistration: () -> Void
    private let navigateToLogin: () -> Void
    private let navigateToDashboard: () -> Void
    private let navigateToCategory: (Int64?) -> Void
    private let navigateToAddExercise: (Int64?) -> Void
    private let viewModel: MainViewModel

    
    init(
        viewModel: MainViewModel,
        navigateToRegistration: @escaping () -> Void,
        navigateToLogin: @escaping () -> Void,
        navigateToDashboard: @escaping () -> Void,
        navigateToCategory: @escaping (Int64?) -> Void,
        navigateToAddExercise: @escaping (Int64?) -> Void
    ) {
        self.viewModel = viewModel
        self.navigateToRegistration = navigateToRegistration
        self.navigateToLogin = navigateToLogin
        self.navigateToDashboard = navigateToDashboard
        self.navigateToCategory = navigateToCategory
        self.navigateToAddExercise = navigateToAddExercise
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
            if(command is NavigationDirections.Category){
                let id = (command as? NavigationDirections.Category)?.getCategoryId()
                navigateToCategory(id)
            }
        }
        
        if(command is NavigationDirections.AddExercise){
            let id = (command as? NavigationDirections.AddExercise)?.getExerciseId()
            navigateToAddExercise(id)
        }
        
        
     }
    
    var body: some View {
            VStack(alignment: .leading) {
                    Text("")
            
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

