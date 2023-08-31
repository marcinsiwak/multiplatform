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

    actual class Welcome actual constructor(override val isInclusive: Boolean) : NavigationDirections() {
        override val route: String
            get() = "welcome"
    }

    actual object Registration : NavigationDirections() {
        override val route: String
            get() = "registration"
    }

    actual class Dashboard actual constructor(override val isInclusive: Boolean): NavigationDirections() {
        override val route: String
            get() = "dashboard"
    }

    actual class AddExercise actual constructor(val id: String) : NavigationDirections() {
        override val route: String
            get() = "addExercise/{$BUNDLE_ARG_ID}"
        override val destination: String
            get() = "addExercise/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }

    actual class CategoryDetails actual constructor(val id: String) : NavigationDirections() {
        override val route: String
            get() = "category/{${BUNDLE_ARG_ID}}"
        override val destination: String
            get() = "category/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }

    actual object AddCategory : NavigationDirections() {
        override val route: String
            get() = "addCategory"
    }

    actual object Language : NavigationDirections() {
        override val route: String
            get() = "language"
    }

    actual object Unit : NavigationDirections() {
        override val route: String
            get() = "unit"
    }

    actual object ForceUpdate : NavigationDirections() {
        override val route: String
            get() = "forceUpdate"

        override val isInclusive: Boolean
            get() = true
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