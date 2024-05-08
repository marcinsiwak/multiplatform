package pl.msiwak.multiplatform.shared.navigation

import pl.msiwak.multiplatform.ui.addCategory.AddCategoryGraph
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseGraph
import pl.msiwak.multiplatform.ui.category.CategoryGraph
import pl.msiwak.multiplatform.ui.dashboard.DashboardGraph
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateGraph
import pl.msiwak.multiplatform.ui.language.LanguageGraph
import pl.msiwak.multiplatform.ui.register.RegisterGraph
import pl.msiwak.multiplatform.ui.unit.UnitGraph
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailGraph
import pl.msiwak.multiplatform.ui.welcome.WelcomeGraph

data class NavigationProvider(
    val welcomeGraph: WelcomeGraph,
    val registerGraph: RegisterGraph,
    val addCategoryGraph: AddCategoryGraph,
    val categoryGraph: CategoryGraph,
    val dashboardGraph: DashboardGraph,
    val addExerciseGraph: AddExerciseGraph,
    val unitGraph: UnitGraph,
    val languageGraph: LanguageGraph,
    val forceUpdateGraph: ForceUpdateGraph,
    val verifyEmailGraph: VerifyEmailGraph
)
