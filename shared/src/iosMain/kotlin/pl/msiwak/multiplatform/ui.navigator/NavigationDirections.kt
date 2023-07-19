package pl.msiwak.multiplatform.ui.navigator


actual sealed class NavigationDirections : NavigationCommand {
    actual override val destination: String
        get() = route

    actual override val isInclusive: Boolean
        get() = false

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

    actual object Unit : NavigationDirections() {
        override val route: String = "unit"
    }

    actual object ForceUpdate : NavigationDirections() {
        override val route: String
            get() = "forceUpdate"
    }

    actual object OpenStore : NavigationDirections() {
        override val route: String
            get() = ""
    }

    actual object VerifyEmail : NavigationDirections() {
        override val route: String
            get() = "registrationSuccess"
    }

}