package pl.msiwak.multiplatform.ui.navigator


expect sealed class NavigationDirections : NavigationCommand {
    override val destination: String

    object NavigateUp : NavigationDirections
    object Welcome : NavigationDirections
    object Registration : NavigationDirections
    object Login : NavigationDirections
    object Dashboard : NavigationDirections
    class AddExercise(id: Long = 0) : NavigationDirections
    class Category(id: Long = 0) : NavigationDirections
    object AddCategory : NavigationDirections
}