import SwiftUI
import shared

struct MainScreen: View {
    let mainDiHelper = MainDiHelper()
    private let navigateToRegistration: () -> Void
    private let navigateToLogin: () -> Void
    private let navigateToDashboard: () -> Void
    private let navigateToCategory: (Int64?) -> Void
    private let navigateToAddExercise: (Int64?) -> Void
    private let navigateToAddCategory: () -> Void
    private let viewModel: MainViewModel

    
    init(
        navigateToRegistration: @escaping () -> Void,
        navigateToLogin: @escaping () -> Void,
        navigateToDashboard: @escaping () -> Void,
        navigateToCategory: @escaping (Int64?) -> Void,
        navigateToAddExercise: @escaping (Int64?) -> Void,
        navigateToAddCategory: @escaping () -> Void
    ) {
        self.viewModel = mainDiHelper.getMainViewModel()
        self.navigateToRegistration = navigateToRegistration
        self.navigateToLogin = navigateToLogin
        self.navigateToDashboard = navigateToDashboard
        self.navigateToCategory = navigateToCategory
        self.navigateToAddExercise = navigateToAddExercise
        self.navigateToAddCategory = navigateToAddCategory
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
        if(command is NavigationDirections.Registration) {
            navigateToRegistration()
        }
        
        if(command is NavigationDirections.Login) {
            navigateToLogin()
        }
        
        if(command is NavigationDirections.Dashboard) {
            navigateToDashboard()
        }
        
        if(command is NavigationDirections.Category){
            let id = (command as? NavigationDirections.Category)?.getCategoryId()
            navigateToCategory(id)
        }
        
        
        if(command is NavigationDirections.AddExercise){
            let id = (command as? NavigationDirections.AddExercise)?.getExerciseId()
            navigateToAddExercise(id)
        }
        
        if(command is NavigationDirections.AddCategory){
            navigateToAddCategory()
        }
        
        
     }
    
    var body: some View {
        
            VStack(alignment: .leading) {            
                    Button("Register") {
//                        mainDiHel.per.onRegistrationClicked()
                        
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

