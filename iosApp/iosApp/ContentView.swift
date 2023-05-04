import SwiftUI
import shared

private let RegistrationRoute = "registration"
private let LoginRoute = "login"
private let DashboardRoute = "dashboard"
private let CategoryRoute = "category"


struct ContentView: View {
    @State private var route : [NavigationDirections] = [NavigationDirections.Dashboard()]
    
    var body: some View {
        NavigationStack(path: $route) {
            MainScreen(
                navigateToRegistration: { route.append(NavigationDirections.Registration()) },
                navigateToLogin: { route.append(NavigationDirections.Login()) },
                navigateToDashboard: { route.append(NavigationDirections.Dashboard()) },
                navigateToCategory: { id in
                    route.append(NavigationDirections.Category(id: id ?? 0))
                },
                navigateToAddExercise: { id in
                    route.append(NavigationDirections.AddExercise(id: id ?? 0))
                },
                navigateToAddCategory: {
                    route.append(NavigationDirections.AddCategory())
                }
            ).navigationDestination(for: NavigationDirections.self) { direction in
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
                        .navigationBarBackButtonHidden(true)
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

