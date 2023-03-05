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

    class AddExercise(private val id: Long = 0) :
        NavigationDirections() {
        override val route: String = "addExercise/{$BUNDLE_ARG_ID}"
        override val destination: String
            get() = "addExercise/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }

    class Category(private val id: Long = 0) : NavigationDirections() {
        override val route: String = "category/{${BUNDLE_ARG_ID}}"
        override val destination: String
            get() = "category/$id"

        companion object {
            const val BUNDLE_ARG_ID = "id"
        }
    }

    object AddCategory : NavigationDirections() {
        override val route: String = "addCategory"
    }
}