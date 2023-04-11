package pl.msiwak.multiplatform.ui.navigator


actual sealed class NavigationDirections : NavigationCommand {
    actual override val destination: String
        get() = route

    actual object NavigateUp : NavigationDirections() {
        override val route: String
            get() = "navigateUp"
    }

    actual object Welcome : NavigationDirections() {
        override val route: String = "welcome"
    }

    actual object Registration : NavigationDirections() {
        override val route: String = "registration"
    }

    actual object Login : NavigationDirections() {
        override val route: String = "login"
    }

    actual object Dashboard : NavigationDirections() {
        override val route: String = "dashboard"
    }

    actual class AddExercise actual constructor(private val id: Long) : NavigationDirections() {
        fun getExerciseId(): Long = id
        override val route: String = "addExercise"
    }

    actual class Category actual constructor(private val id: Long) : NavigationDirections() {
        fun getCategoryId(): Long = id
        override val route: String = "category"
    }

    actual object AddCategory : NavigationDirections() {
        override val route: String = "addCategory"
    }

    actual object Language : NavigationDirections() {
        override val route: String = "language"
    }
}