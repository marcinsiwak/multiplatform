package pl.msiwak.multiplatform.navigator

actual sealed class NavigationDirections : NavigationCommand {
    actual override val destination: String
        get() = route

    actual override val isInclusive: Boolean
        get() = false

    actual object NavigateUp : NavigationDirections() {
        override val route: String
            get() = "navigateUp"
    }

    actual class Welcome actual constructor(override val isInclusive: Boolean) :
        NavigationDirections() {
        override val route: String
            get() = "welcome"
    }

    actual object Registration : NavigationDirections() {
        override val route: String = "registration"
    }

    actual class Dashboard actual constructor(override val isInclusive: Boolean) :
        NavigationDirections() {
        override val route: String
            get() = "dashboard"
    }

    actual class AddExercise actual constructor(private val id: String) : NavigationDirections() {
        fun getExerciseId(): String = id
        override val route: String = "addExercise"
    }

    actual class CategoryDetails actual constructor(private val id: String) :
        NavigationDirections() {
        fun getCategoryId(): String = id
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
