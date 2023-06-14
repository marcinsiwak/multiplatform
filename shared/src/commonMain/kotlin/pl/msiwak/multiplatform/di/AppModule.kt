package pl.msiwak.multiplatform.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.api.remoteConfig.RemoteConfig
import pl.msiwak.multiplatform.data.store.LanguageStore
import pl.msiwak.multiplatform.data.store.UnitStore
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExerciseDao
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.SetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.domain.summaries.GetCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.GetCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.GetExerciseDataUseCase
import pl.msiwak.multiplatform.domain.summaries.GetExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.GetExercisesUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertExercisesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseUseCase
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.repository.CategoryRepository
import pl.msiwak.multiplatform.repository.ExerciseRepository
import pl.msiwak.multiplatform.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.repository.VersionRepository
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel
import pl.msiwak.multiplatform.ui.category.CategoryViewModel
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel
import pl.msiwak.multiplatform.ui.language.LanguageViewModel
import pl.msiwak.multiplatform.ui.login.LoginViewModel
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.ui.unit.UnitViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter
import pl.msiwak.multiplatform.validators.Validator

fun appModule() = listOf(
    platformModule,
    apiModule,
    viewModelsModule,
    databaseModule,
    useCaseModule,
    toolsModule,
    repositoryUseModule,
    storeModule
)

val databaseModule = module {
    single { Database(get()) }
    single { ExerciseDao(get()) }
    single { CategoriesDao(get()) }
}

val storeModule = module {
    single { LanguageStore(get()) }
    single { UnitStore(get()) }
}

val apiModule = module {
    single { FirebaseAuthorization() }
    single { RemoteConfig() }
}

val toolsModule = module {
    single { Navigator() }
    single { GlobalErrorHandler() }
    factory { Validator() }
    factory { DateFormatter() }
    factory { NumberFormatter() }
}

val viewModelsModule = module {
    viewModelDefinition { MainViewModel(get(), get(), get(), get(), get(), get()) }
    viewModelDefinition { RegisterViewModel(get(), get(), get()) }
    viewModelDefinition { LoginViewModel(get(), get(), get()) }
    viewModelDefinition { WelcomeScreenViewModel(get()) }
    viewModelDefinition { SummaryViewModel(get(), get(), get()) }
    viewModelDefinition { params ->
        AddExerciseViewModel(
            id = params.get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition { params ->
        CategoryViewModel(
            id = params.get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition { AddCategoryViewModel(get(), get()) }
    viewModelDefinition { SettingsViewModel(get(), get()) }
    viewModelDefinition { LanguageViewModel(get(), get()) }
    viewModelDefinition { UnitViewModel(get(), get()) }
    viewModelDefinition { ForceUpdateViewModel(get()) }
}

val useCaseModule = module {
    factory { RegisterUserUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { SaveUserTokenUseCase(get()) }
    factory { GetUserTokenUseCase(get()) }
    factory { GetExercisesUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { InsertCategoriesUseCase(get()) }
    factory { InsertCategoryUseCase(get()) }
    factory { UpdateCategoriesUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
    factory { ObserveCategoryUseCase(get()) }
    factory { ObserveCategoriesUseCase(get()) }
    factory { InsertExercisesUseCase(get()) }
    factory { InsertExerciseUseCase(get()) }
    factory { UpdateExerciseUseCase(get(), get()) }
    factory { GetExerciseDataUseCase(get()) }
    factory { GetExerciseUseCase(get(), get()) }
    factory { RemoveExerciseUseCase(get()) }
    factory { FormatDateUseCase(get()) }
    factory { FormatResultsUseCase(get(), get()) }
    factory { FormatStringToDateUseCase(get()) }
    factory { SetLanguageUseCase(get()) }
    factory { GetLanguageUseCase(get()) }
    factory { GetUnitsUseCase(get()) }
    factory { SetUnitsUseCase(get()) }
    factory { FetchRemoteConfigUseCase(get()) }
    factory { GetMinAppCodeUseCase(get()) }
    factory { GetCurrentAppCodeUseCase(get()) }
    factory { GetVersionNameUseCase(get()) }
}

val repositoryUseModule = module {
    single { AuthRepository(get()) }
    single { ExerciseRepository(get()) }
    single { CategoryRepository(get()) }
    single { RemoteConfigRepository(get()) }
    single { VersionRepository(get()) }
}
