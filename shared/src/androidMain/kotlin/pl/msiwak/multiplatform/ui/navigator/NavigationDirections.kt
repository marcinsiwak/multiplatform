package pl.msiwak.multiplatform.ui.navigator

actual sealed class NavigationDirections : NavigationCommand {
    actual override val destination: String
        get() = route

    actual object NavigateUp : NavigationDirections() {
        override val route: String
            get() = "navigateUp"
    }

    actual object Welcome : NavigationDirections() {
        override val route: String
            get() = "welcome"
    }

    actual object Registration : NavigationDirections() {
        override val route: String
            get() = "registration"
    }

    actual object Login : NavigationDirections() {
        override val route: String
            get() = "login"
    }

    actual object Dashboard : NavigationDirections() {
        override val route: String
            get() = "dashboard"
    }

    actual class AddExercise actual constructor(val id: Long) : NavigationDirections() {
        override val route: String
            get() = "addExercise/{$BUNDLE_ARG_ID}"
        override val destination: String
            get() = "addExercise/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }

    actual class Category actual constructor(val id: Long) : NavigationDirections() {
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

}