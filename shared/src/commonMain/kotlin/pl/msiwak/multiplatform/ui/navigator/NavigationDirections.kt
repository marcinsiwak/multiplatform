package pl.msiwak.multiplatform.ui.navigator


expect sealed class NavigationDirections : NavigationCommand {
    override val destination: String
    override val isInclusive: Boolean

    object NavigateUp : NavigationDirections
    class Welcome(isInclusive: Boolean = false) : NavigationDirections
    object Registration : NavigationDirections
    object Dashboard : NavigationDirections
    class AddExercise(id: Long = 0) : NavigationDirections
    class Category(id: Long = 0) : NavigationDirections
    object AddCategory : NavigationDirections
    object Language: NavigationDirections
    object Unit: NavigationDirections
    object ForceUpdate : NavigationDirections
    object OpenStore : NavigationDirections
    object VerifyEmail : NavigationDirections
}