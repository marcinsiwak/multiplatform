import SwiftUI
import shared


struct ContentView: View {
    @State private var route : [NavigationDirections] = []
    private let viewModel: MainViewModel = MainDiHelper().getMainViewModel()
    

    var body: some View {
        NavigationStack(path: $route) {
            SplashScreen(
                navigate: { command in
                    route.append(command)
                },
                navigateBack: {
                    route.removeLast()
                }
            ).navigationDestination(for: NavigationDirections.self) { direction in
                if(direction is NavigationDirections.Welcome) {
                    WelcomeScreen()
                        .navigationBarBackButtonHidden(true)
                }
                if(direction is NavigationDirections.Registration) {
                    RegisterScreen()
                }
                if(direction is NavigationDirections.Dashboard) {
                    DashboardScreen().navigationBarBackButtonHidden(true)
                }
                if(direction is NavigationDirections.Category) {
                    let id = (direction as? NavigationDirections.Category)?.getCategoryId() ?? 0
                    CategoryScreen(id: id)
                        .navigationBarTitleDisplayMode(.inline)
                }
                if(direction is NavigationDirections.AddExercise) {
                    let id = (direction as? NavigationDirections.AddExercise)?.getExerciseId() ?? 0
                    AddExerciseScreen(id: id)
                    .navigationBarTitleDisplayMode(.inline)
                }
                if(direction is NavigationDirections.AddCategory) {
                    AddCategoryScreen()
                        .navigationBarTitleDisplayMode(.inline)
                }
            }
        }
        .tint(.white)
    }
}



//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}

