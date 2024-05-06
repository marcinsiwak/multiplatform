package pl.msiwak.multiplatform.navigator.destination

import pl.msiwak.multiplatform.commonResources.SR

sealed class NavDestination(val route: String) {
    //    object NavigateUp : NavDestination()
    sealed class WelcomeDestination(route: String) : NavDestination(route) {
        object NavWelcomeGraphDestination : WelcomeDestination("welcome_graph")
        object NavWelcomeScreen : WelcomeDestination("welcome_screen")
    }

    sealed class RegistrationDestination(route: String) : NavDestination(route) {
        object NavRegistrationGraphDestination : RegistrationDestination("registration_graph")
        object NavRegistrationScreen : RegistrationDestination("registration_screen")
    }

    sealed class DashboardDestination(route: String) : NavDestination(route) {
        object NavDashboardGraphDestination : DashboardDestination("dashboard_graph")
        object NavDashboardScreen : DashboardDestination("dashboard_screen")

        sealed class SummaryDestination(route: String) : DashboardDestination(route) {
            object NavSummaryGraphDestination : BottomNavigationDestination(
                SR.images.ic_workout,
                SR.strings.summary,
                "summary_graph"
            )

            object NavSummaryScreen : SummaryDestination("summary_screen")
        }

        sealed class SettingsDestination(route: String) : DashboardDestination(route) {
            object NavSettingsGraphDestination : BottomNavigationDestination(
                SR.images.ic_settings,
                SR.strings.settings,
                "settings_graph"
            )

            object NavSettingsScreen : SettingsDestination("settings_screen")
        }
    }

    sealed class AddExerciseDestination(route: String) : NavDestination(route) {
        object NavAddExerciseGraphDestination : AddExerciseDestination("add_exercise_graph")
        object NavAddExerciseScreen : AddExerciseDestination("add_exercise_screen") {
            fun route(id: String) {

            }
        }
    }

    sealed class CategoryDestination(route: String) : NavDestination(route) {
        object NavCategoryGraphDestination :
            CategoryDestination("category_details_graph")

        object NavCategoryScreen : CategoryDestination("category_details_screen") {
            fun route(id: String) {

            }
        }
    }

    sealed class AddCategoryDestination(route: String) : NavDestination(route) {
        object NavAddCategoryGraphDestination : AddCategoryDestination("add_category_graph")
        object NavAddCategoryScreen : AddCategoryDestination("add_category_screen") {
            fun route(id: String) {

            }
        }
    }

    sealed class LanguageDestination(route: String) : NavDestination(route) {
        object NavLanguageGraphDestination : LanguageDestination("language_graph")
        object NavLanguageScreen : LanguageDestination("language_screen")
    }

    sealed class UnitDestination(route: String) : NavDestination(route) {
        object NavUnitGraphDestination : UnitDestination("unit_graph")
        object NavUnitScreen : UnitDestination("unit_screen")
    }

    sealed class ForceUpdateDestination(route: String) : NavDestination(route) {
        object NavForceUpdateGraphDestination : ForceUpdateDestination("force_update_graph")
        object NavForceUpdateScreen : ForceUpdateDestination("force_update_screen")
    }

    sealed class VerifyEmailDestination(route: String) : NavDestination(route) {
        object NavVerifyEmailGraphDestination : VerifyEmailDestination("verify_email_graph")
        object NavVerifyEmailScreen : VerifyEmailDestination("verify_email_screen")
    }
//    object OpenStore : NavDestination()
}
