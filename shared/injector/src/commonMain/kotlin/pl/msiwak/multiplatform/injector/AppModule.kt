package pl.msiwak.multiplatform.injector

import org.koin.dsl.module
import pl.msiwak.multiplatform.auth.FirebaseAuthorization
import pl.msiwak.multiplatform.auth.SessionStore
import pl.msiwak.multiplatform.data.local.store.LanguageStore
import pl.msiwak.multiplatform.data.local.store.UnitStore
import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.data.remote.repository.UserRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.SetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCase
import pl.msiwak.multiplatform.domain.summaries.AddExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.network.client.CategoryClient
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.network.client.UserClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.mapper.UserMapper
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.network.service.UserService
import pl.msiwak.multiplatform.remoteConfig.RemoteConfig
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel
import pl.msiwak.multiplatform.ui.category.CategoryViewModel
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel
import pl.msiwak.multiplatform.ui.language.LanguageViewModel
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.ui.unit.UnitViewModel
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.validators.Validator

fun appModule() = listOf(
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
    viewModelDefinition { SummaryViewModel(get(), get(), get(), get(), get()) }
    viewModelDefinition { params ->
        AddExerciseViewModel(
            id = params.get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    viewModelDefinition { params ->
        CategoryViewModel(
            id = params.get(),
            get(),
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
    factory { DownloadCategoriesUseCase(get()) }
    factory { DownloadCategoryUseCase(get()) }
    factory { InsertCategoriesUseCase(get()) }
    factory { CreateCategoryUseCase(get()) }
    factory { UpdateCategoriesUseCase(get()) }
    factory { ObserveCategoryUseCase(get()) }
    factory { ObserveCategoriesUseCase(get()) }
    factory { UpdateExerciseUseCase(get()) }
    factory { RemoveExerciseUseCase(get()) }
    factory { RemoveCategoryUseCase(get()) }
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
    factory { AddExerciseUseCase(get()) }
}

val repositoryUseModule = module {
    single { AuthRepository(get()) }
    single { UserRepository(get()) }
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