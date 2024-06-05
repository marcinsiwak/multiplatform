package pl.msiwak.multiplatform.navigator.destination

import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.ic_settings
import athletetrack.shared.commonresources.generated.resources.ic_workout
import athletetrack.shared.commonresources.generated.resources.settings
import athletetrack.shared.commonresources.generated.resources.summary
import pl.msiwak.multiplatform.navigator.ADD_CATEGORY_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.ARG_CATEGORY_ID
import pl.msiwak.multiplatform.navigator.ARG_EXERCISE_ID
import pl.msiwak.multiplatform.navigator.ARG_EXERCISE_TYPE
import pl.msiwak.multiplatform.navigator.CATEGORY_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.DASHBOARD_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.EXERCISE_CHART_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.EXERCISE_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.FORCE_UPDATE_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.LANGUAGE_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.REGISTRATION_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.SETTINGS_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.SUMMARY_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.UNIT_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.VERIFY_EMAIL_SCREEN_ROUTE
import pl.msiwak.multiplatform.navigator.WELCOME_SCREEN_ROUTE

sealed class NavDestination(val route: String) {
    sealed class WelcomeDestination(route: String) : NavDestination(route) {
        object NavWelcomeGraphDestination : WelcomeDestination("welcome_graph")
        object NavWelcomeScreen : WelcomeDestination(WELCOME_SCREEN_ROUTE)
    }

    sealed class RegistrationDestination(route: String) : NavDestination(route) {
        object NavRegistrationGraphDestination : RegistrationDestination("registration_graph")
        object NavRegistrationScreen : RegistrationDestination(REGISTRATION_SCREEN_ROUTE)
    }

    sealed class DashboardDestination(route: String) : NavDestination(route) {
        object NavDashboardGraphDestination : DashboardDestination("dashboard_graph")
        object NavDashboardScreen : DashboardDestination(DASHBOARD_SCREEN_ROUTE)

        sealed class SummaryDestination(route: String) : DashboardDestination(route) {
            object NavSummaryGraphDestination : BottomNavigationDestination(
                Res.drawable.ic_workout,
                Res.string.summary,
                "summary_graph",
                NavSummaryScreen.route
            )

            object NavSummaryScreen : SummaryDestination(SUMMARY_SCREEN_ROUTE)
        }

        sealed class SettingsDestination(route: String) : DashboardDestination(route) {
            object NavSettingsGraphDestination : BottomNavigationDestination(
                Res.drawable.ic_settings,
                Res.string.settings,
                "settings_graph",
                NavSettingsScreen.route
            )

            object NavSettingsScreen : SettingsDestination(SETTINGS_SCREEN_ROUTE)
        }
    }

    sealed class ExerciseDestination(route: String) : NavDestination(route) {
        object NavExerciseGraphDestination : ExerciseDestination("add_exercise_graph")
        object NavExerciseScreen :
            ExerciseDestination("$EXERCISE_SCREEN_ROUTE/{$ARG_EXERCISE_ID}") {
            fun route(id: String): String {
                return "$EXERCISE_SCREEN_ROUTE/$id"
            }
        }

        object NavExerciseChartScreen :
            ExerciseDestination(
                "$EXERCISE_CHART_SCREEN_ROUTE/{$ARG_EXERCISE_ID}/{$ARG_EXERCISE_TYPE}"
            ) {
            fun route(exerciseId: String, exerciseType: String): String {
                return "$EXERCISE_CHART_SCREEN_ROUTE/$exerciseId/$exerciseType"
            }
        }
    }

    sealed class CategoryDestination(route: String) : NavDestination(route) {
        object NavCategoryGraphDestination :
            CategoryDestination("category_details_graph")

        object NavCategoryScreen :
            CategoryDestination("$CATEGORY_SCREEN_ROUTE/{$ARG_CATEGORY_ID}") {
            fun route(id: String): String {
                return "$CATEGORY_SCREEN_ROUTE/$id"
            }
        }
    }

    sealed class AddCategoryDestination(route: String) : NavDestination(route) {
        object NavAddCategoryGraphDestination : AddCategoryDestination("add_category_graph")
        object NavAddCategoryScreen : AddCategoryDestination(ADD_CATEGORY_SCREEN_ROUTE)
    }

    sealed class LanguageDestination(route: String) : NavDestination(route) {
        object NavLanguageGraphDestination : LanguageDestination("language_graph")
        object NavLanguageScreen : LanguageDestination(LANGUAGE_SCREEN_ROUTE)
    }

    sealed class UnitDestination(route: String) : NavDestination(route) {
        object NavUnitGraphDestination : UnitDestination("unit_graph")
        object NavUnitScreen : UnitDestination(UNIT_SCREEN_ROUTE)
    }

    sealed class ForceUpdateDestination(route: String) : NavDestination(route) {
        object NavForceUpdateGraphDestination : ForceUpdateDestination("force_update_graph")
        object NavForceUpdateScreen : ForceUpdateDestination(FORCE_UPDATE_SCREEN_ROUTE)
    }

    sealed class VerifyEmailDestination(route: String) : NavDestination(route) {
        object NavVerifyEmailGraphDestination : VerifyEmailDestination("verify_email_graph")
        object NavVerifyEmailScreen : VerifyEmailDestination(VERIFY_EMAIL_SCREEN_ROUTE)
    }
}
