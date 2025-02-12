package pl.msiwak.multiplatform.shared.modules

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pl.msiwak.multiplatform.shared.main.MainViewModel
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel
import pl.msiwak.multiplatform.ui.adminpanel.AdminPanelViewModel
import pl.msiwak.multiplatform.ui.category.CategoryViewModel
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel
import pl.msiwak.multiplatform.ui.language.LanguageViewModel
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.ui.unit.UnitViewModel
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.ui.welcome.terms.TermsConfirmationViewModel

val viewModelsModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { VerifyEmailViewModel(get(), get()) }
    viewModel { WelcomeScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { TermsConfirmationViewModel(get(), get()) }
    viewModel { SummaryViewModel(get(), get(), get(), get()) }
    viewModel { AddExerciseViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { CategoryViewModel(get(), get(), get(), get(), get()) }
    viewModel { AddCategoryViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
    viewModel { LanguageViewModel(get(), get()) }
    viewModel { UnitViewModel(get(), get()) }
    viewModel { ForceUpdateViewModel() }
    viewModel { DashboardViewModel(get()) }
    viewModel { AdminPanelViewModel(get(), get(), get()) }
}