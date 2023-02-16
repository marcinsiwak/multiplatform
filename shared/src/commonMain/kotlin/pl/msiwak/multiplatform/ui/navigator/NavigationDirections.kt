package pl.msiwak.multiplatform.ui.navigator


sealed class NavigationDirections : NavigationCommand {
    override val destination: String
        get() = route

    object NavigateUp : NavigationDirections() {
        override val route: String
            get() = "navigateUp"
    }

    object Welcome : NavigationDirections() {
        override val route: String = "welcome"
    }

    object Registration : NavigationDirections() {
        override val route: String = "registration"
    }

    object Login : NavigationDirections() {
        override val route: String = "login"
    }

    object Dashboard : NavigationDirections() {
        override val route: String = "dashboard"
    }

    object AddExercise : NavigationDirections() {
        override val route: String = "addExercise"
    }

    class Exercise(private val id: Long = 0) :
        NavigationDirections() {
        override val route: String = "exercise/{$BUNDLE_ARG_ID}"
        override val destination: String
            get() = "exercise/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }
}