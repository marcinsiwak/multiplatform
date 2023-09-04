package pl.msiwak.multiplatform.core.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.core.api.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.core.api.client.CategoryClient
import pl.msiwak.multiplatform.core.api.client.KtorClient
import pl.msiwak.multiplatform.core.api.client.UserClient
import pl.msiwak.multiplatform.core.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.core.api.remoteConfig.RemoteConfig
import pl.msiwak.multiplatform.core.api.service.CategoryService
import pl.msiwak.multiplatform.core.api.service.UserService
import pl.msiwak.multiplatform.core.data.store.LanguageStore
import pl.msiwak.multiplatform.core.data.store.SessionStore
import pl.msiwak.multiplatform.core.data.store.UnitStore
import pl.msiwak.multiplatform.core.database.Database
import pl.msiwak.multiplatform.core.database.dao.CategoriesDao
import pl.msiwak.multiplatform.core.database.dao.ExerciseDao
import pl.msiwak.multiplatform.core.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.core.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.core.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.core.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.core.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.core.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.core.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.core.domain.authorization.SaveUserTokenUseCase
import pl.msiwak.multiplatform.core.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.core.domain.remoteConfig.GetMinAppCodeUseCase
import pl.msiwak.multiplatform.core.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.core.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.core.domain.settings.SetLanguageUseCase
import pl.msiwak.multiplatform.core.domain.settings.SetUnitsUseCase
import pl.msiwak.multiplatform.core.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.core.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.core.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.core.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.GetCategoryUseCase
import pl.msiwak.multiplatform.core.domain.summaries.GetExerciseDataUseCase
import pl.msiwak.multiplatform.core.domain.summaries.GetExerciseUseCase
import pl.msiwak.multiplatform.core.domain.summaries.GetExercisesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.InsertCategoriesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.core.domain.summaries.InsertExerciseUseCase
import pl.msiwak.multiplatform.core.domain.summaries.InsertExercisesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.core.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.core.domain.summaries.UpdateCategoriesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.UpdateExerciseUseCase
import pl.msiwak.multiplatform.core.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.core.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.core.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.core.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.core.mapper.CategoryMapper
import pl.msiwak.multiplatform.core.mapper.ExerciseMapper
import pl.msiwak.multiplatform.core.mapper.UserMapper
import pl.msiwak.multiplatform.core.repository.AuthRepository
import pl.msiwak.multiplatform.core.repository.CategoryRepository
import pl.msiwak.multiplatform.core.repository.ExerciseRepository
import pl.msiwak.multiplatform.core.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.core.repository.SessionRepository
import pl.msiwak.multiplatform.core.repository.UserRepository
import pl.msiwak.multiplatform.repository.VersionRepository
import pl.msiwak.multiplatform.core.ui.addCategory.AddCategoryViewModel
import pl.msiwak.multiplatform.core.ui.addExercise.AddExerciseViewModel
import pl.msiwak.multiplatform.core.ui.category.CategoryViewModel
import pl.msiwak.multiplatform.core.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.core.ui.forceUpdate.ForceUpdateViewModel
import pl.msiwak.multiplatform.core.ui.language.LanguageViewModel
import pl.msiwak.multiplatform.core.ui.main.MainViewModel
import pl.msiwak.multiplatform.core.ui.navigator.Navigator
import pl.msiwak.multiplatform.core.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.core.ui.settings.SettingsViewModel
import pl.msiwak.multiplatform.core.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.core.ui.unit.UnitViewModel
import pl.msiwak.multiplatform.core.ui.verifyEmail.VerifyEmailViewModel
import pl.msiwak.multiplatform.core.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.core.utils.DateFormatter
import pl.msiwak.multiplatform.core.utils.NumberFormatter
import pl.msiwak.multiplatform.core.validators.Validator

fun appModule() = listOf(
    platformModule,
    apiModule,
    viewModelsModule,
    databaseModule,
    useCaseModule,
    toolsModule,
    repositoryUseModule,
    storeModule,
    serviceModule,
    clientModule
)

val databaseModule = module {
    single { Database(get()) }
    single { ExerciseDao(get()) }
    single { CategoriesDao(get()) }
}

val storeModule = module {
    single { LanguageStore(get()) }
    single { UnitStore(get()) }
    single { SessionStore(get()) }
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
    factory { UserMapper() }
    factory { ExerciseMapper() }
    factory { CategoryMapper(get()) }
}

val viewModelsModule = module {
    viewModelDefinition { MainViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModelDefinition { RegisterViewModel(get(), get(), get(), get()) }
    viewModelDefinition { VerifyEmailViewModel(get(), get()) }
    viewModelDefinition { WelcomeScreenViewModel(get(), get(), get(), get()) }
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
    viewModelDefinition { SettingsViewModel(get(), get(), get()) }
    viewModelDefinition { LanguageViewModel(get(), get()) }
    viewModelDefinition { UnitViewModel(get(), get()) }
    viewModelDefinition { ForceUpdateViewModel(get()) }
    viewModelDefinition { DashboardViewModel(get(), get()) }
}

val useCaseModule = module {
    factory { RegisterUserUseCase(get()) }
    factory { LoginUseCase(get(), get()) }
    factory { GoogleLoginUseCase(get(), get()) }
    factory { LogoutUseCase(get()) }
    factory { SaveUserTokenUseCase(get()) }
    factory { GetUserTokenUseCase(get()) }
    factory { GetExercisesUseCase(get()) }
    factory { DownloadCategoriesUseCase(get()) }
    factory { InsertCategoriesUseCase(get()) }
    factory { CreateCategoryUseCase(get()) }
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
    factory { GetForceUpdateStateUseCase(get(), get()) }
    factory { GetMinAppCodeUseCase(get()) }
    factory { GetCurrentAppCodeUseCase(get()) }
    factory { GetVersionNameUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { ObserveAuthStateChangedUseCase(get(), get()) }
    factory { ResendVerificationEmailUseCase(get()) }
}

val repositoryUseModule = module {
    single { AuthRepository(get()) }
    single { UserRepository(get()) }
    single { ExerciseRepository(get()) }
    single { CategoryRepository(get(), get()) }
    single { RemoteConfigRepository(get()) }
    single { VersionRepository(get()) }
    single { SessionRepository(get()) }
}

val serviceModule = module {
    single { UserService(get(), get()) }
    single { CategoryService(get(), get()) }
}

val clientModule = module {
    single { KtorClient(get()) }
    single { UserClient(get()) }
    single { CategoryClient(get()) }
}