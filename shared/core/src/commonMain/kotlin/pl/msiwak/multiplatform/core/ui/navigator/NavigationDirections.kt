package pl.msiwak.multiplatform.core.ui.navigator


expect sealed class NavigationDirections : NavigationCommand {
    override val destination: String
    override val isInclusive: Boolean

    object NavigateUp : NavigationDirections
    class Welcome(isInclusive: Boolean = false) : NavigationDirections
    object Registration : NavigationDirections
    class Dashboard(isInclusive: Boolean = false) : NavigationDirections
    class AddExercise(id: String = "") : NavigationDirections
    class CategoryDetails(id: String = "") : NavigationDirections
    object AddCategory : NavigationDirections
    object Language : NavigationDirections
    object Unit : NavigationDirections
    object ForceUpdate : NavigationDirections
    object OpenStore : NavigationDirections
    object VerifyEmail : NavigationDirections
}