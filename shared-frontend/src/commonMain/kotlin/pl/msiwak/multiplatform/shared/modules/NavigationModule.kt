package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.shared.navigation.NavigationProvider
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryGraph
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseGraph
import pl.msiwak.multiplatform.ui.adminpanel.AdminPanelGraph
import pl.msiwak.multiplatform.ui.category.CategoryGraph
import pl.msiwak.multiplatform.ui.dashboard.BottomNavigationProvider
import pl.msiwak.multiplatform.ui.dashboard.DashboardGraph
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateGraph
import pl.msiwak.multiplatform.ui.language.LanguageGraph
import pl.msiwak.multiplatform.ui.register.RegisterGraph
import pl.msiwak.multiplatform.ui.settings.SettingsGraph
import pl.msiwak.multiplatform.ui.summary.SummaryGraph
import pl.msiwak.multiplatform.ui.terms.TermsGraph
import pl.msiwak.multiplatform.ui.unit.UnitGraph
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailGraph
import pl.msiwak.multiplatform.ui.welcome.WelcomeGraph

val navigationModule = module {
    single { WelcomeGraph() }
    single { RegisterGraph() }
    single { TermsGraph() }
    single { AddCategoryGraph() }
    single { CategoryGraph() }
    single { DashboardGraph() }
    single { AddExerciseGraph() }
    single { UnitGraph() }
    single { LanguageGraph() }
    single { ForceUpdateGraph() }
    single { VerifyEmailGraph() }
    single { SummaryGraph() }
    single { SettingsGraph() }
    single { AdminPanelGraph() }
    single { NavigationProvider(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    single { BottomNavigationProvider(get(), get()) }
}