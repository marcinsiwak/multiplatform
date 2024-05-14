package pl.msiwak.multiplatform.navigator.destination

import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.ic_settings
import athletetrack.shared.commonresources.generated.resources.ic_workout
import athletetrack.shared.commonresources.generated.resources.settings
import athletetrack.shared.commonresources.generated.resources.summary

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
                Res.drawable.ic_workout,
                Res.string.summary,
                "summary_graph"
            )

            object NavSummaryScreen : SummaryDestination("summary_screen")
        }

        sealed class SettingsDestination(route: String) : DashboardDestination(route) {
            object NavSettingsGraphDestination : BottomNavigationDestination(
                Res.drawable.ic_settings,
                Res.string.settings,
                "settings_graph"
            )

            object NavSettingsScreen : SettingsDestination("settings_screen")
        }
    }

    sealed class AddExerciseDestination(route: String) : NavDestination(route) {
        object NavAddExerciseGraphDestination : AddExerciseDestination("add_exercise_graph")
        object NavAddExerciseScreen : AddExerciseDestination("add_exercise_screen") {
            fun route(id: String): String {
                return id
            }
        }
    }

    sealed class CategoryDestination(route: String) : NavDestination(route) {
        object NavCategoryGraphDestination :
            CategoryDestination("category_details_graph")

        object NavCategoryScreen : CategoryDestination("category_details_screen") {
            fun route(id: String): String {
                return id
            }
        }
    }

    sealed class AddCategoryDestination(route: String) : NavDestination(route) {
        object NavAddCategoryGraphDestination : AddCategoryDestination("add_category_graph")
        object NavAddCategoryScreen : AddCategoryDestination("add_category_screen") {
            fun route(id: String): String {
                return id
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
