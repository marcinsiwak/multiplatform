package pl.msiwak.multiplatform.shared

import org.koin.dsl.module
import pl.msiwak.multiplatform.auth.FirebaseAuthorization
import pl.msiwak.multiplatform.auth.SessionStore
import pl.msiwak.multiplatform.data.local.store.LanguageStore
import pl.msiwak.multiplatform.data.local.store.OfflineStore
import pl.msiwak.multiplatform.data.local.store.UnitStore
import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.data.remote.repository.UserRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCase
import pl.msiwak.multiplatform.domain.authorization.GoogleLoginUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCase
import pl.msiwak.multiplatform.domain.authorization.LogoutUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCase
import pl.msiwak.multiplatform.domain.authorization.ResendVerificationEmailUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCase
import pl.msiwak.multiplatform.domain.authorization.SynchronizeDatabaseUseCaseImpl
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCase
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCaseImpl
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCase
import pl.msiwak.multiplatform.domain.offline.SetOfflineModeUseCaseImpl
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCaseImpl
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCaseImpl
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCaseImpl
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCaseImpl
import pl.msiwak.multiplatform.domain.settings.SetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.SetLanguageUseCaseImpl
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.AddExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.AddExerciseUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.AddResultUseCase
import pl.msiwak.multiplatform.domain.summaries.AddResultUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoryUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.DownloadExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadExerciseUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatMillisecondsToRunningAmountUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatMillisecondsToRunningAmountUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountToMillisecondsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountToMillisecondsUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.ObserveExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveExerciseUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.RemoveResultUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveResultUseCaseImpl
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseNameUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseNameUseCaseImpl
import pl.msiwak.multiplatform.domain.user.CreateUserUseCase
import pl.msiwak.multiplatform.domain.user.CreateUserUseCaseImpl
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCaseImpl
import pl.msiwak.multiplatform.network.EngineProvider
import pl.msiwak.multiplatform.network.api.CategoryApi
import pl.msiwak.multiplatform.network.api.UserApi
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.mapper.ResultMapper
import pl.msiwak.multiplatform.network.mapper.UserMapper
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.network.service.UserService
import pl.msiwak.multiplatform.remoteConfig.RemoteConfig
import pl.msiwak.multiplatform.shared.navigation.NavigationProvider
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryGraph
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseGraph
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel
import pl.msiwak.multiplatform.ui.category.CategoryGraph
import pl.msiwak.multiplatform.ui.category.CategoryViewModel
import pl.msiwak.multiplatform.ui.dashboard.BottomNavigationProvider
import pl.msiwak.multiplatform.ui.dashboard.DashboardGraph
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateGraph
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel
import pl.msiwak.multiplatform.ui.language.LanguageGraph
import pl.msiwak.multiplatform.ui.language.LanguageViewModel
import pl.msiwak.multiplatform.ui.register.RegisterGraph
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.settings.SettingsGraph
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel
import pl.msiwak.multiplatform.ui.summary.SummaryGraph
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.ui.terms.TermsGraph
import pl.msiwak.multiplatform.ui.unit.UnitGraph
import pl.msiwak.multiplatform.ui.unit.UnitViewModel
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailGraph
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeGraph
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.ui.welcome.terms.TermsConfirmationViewModel
import pl.msiwak.multiplatform.utils.DateFormatter
import pl.msiwak.multiplatform.utils.NumberFormatter
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.validators.ResultValidator
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
    clientModule,
    navigationModule
)

val databaseModule = module {
    single { Database(get()) }
    single { CategoriesDao(get()) }
    single { ExercisesDao(get()) }
    single { ResultsDao(get()) }
}

val storeModule = module {
    single { LanguageStore(get()) }
    single { UnitStore(get()) }
    single { OfflineStore(get()) }
    single { SessionStore(get()) }
}

val apiModule = module {
    single { FirebaseAuthorization() }
    single { RemoteConfig() }
}

val toolsModule = module {
    single { GlobalErrorHandler() }
    single { Validator() }
    single { ResultValidator() }
    single { DateFormatter() }
    single { NumberFormatter() }
    single { UserMapper() }
    single { ExerciseMapper(get()) }
    single { ResultMapper() }
    single { CategoryMapper(get()) }
}

val viewModelsModule = module {
    viewModelDefinition {
        MainViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition { RegisterViewModel(get(), get(), get()) }
    viewModelDefinition { VerifyEmailViewModel(get(), get()) }
    viewModelDefinition { WelcomeScreenViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModelDefinition { TermsConfirmationViewModel(get(), get(), get(), get()) }
    viewModelDefinition { SummaryViewModel(get(), get(), get(), get()) }
    viewModelDefinition {
        AddExerciseViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition {
        CategoryViewModel(
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
    viewModelDefinition { ForceUpdateViewModel() }
    viewModelDefinition { DashboardViewModel(get()) }
}

val useCaseModule = module {
    single<RegisterUserUseCase> { RegisterUserUseCaseImpl(get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get(), get()) }
    single<GoogleLoginUseCase> { GoogleLoginUseCaseImpl(get(), get()) }
    single<LogoutUseCase> { LogoutUseCaseImpl(get(), get()) }
    single<SaveUserTokenUseCase> { SaveUserTokenUseCaseImpl(get()) }
    single<GetUserTokenUseCase> { GetUserTokenUseCaseImpl(get()) }
    single<DownloadCategoriesUseCase> { DownloadCategoriesUseCaseImpl(get()) }
    single<DownloadCategoryUseCase> { DownloadCategoryUseCaseImpl(get()) }
    single<CreateCategoryUseCase> { CreateCategoryUseCaseImpl(get()) }
    single<ObserveCategoryUseCase> { ObserveCategoryUseCaseImpl(get()) }
    single<ObserveCategoriesUseCase> { ObserveCategoriesUseCaseImpl(get()) }
    single<UpdateExerciseNameUseCase> { UpdateExerciseNameUseCaseImpl(get()) }
    single<RemoveExerciseUseCase> { RemoveExerciseUseCaseImpl(get()) }
    single<RemoveCategoryUseCase> { RemoveCategoryUseCaseImpl(get()) }
    single<FormatDateUseCase> { FormatDateUseCaseImpl(get()) }
    single<FormatResultsUseCase> { FormatResultsUseCaseImpl(get(), get(), get()) }
    single<FormatStringToDateUseCase> { FormatStringToDateUseCaseImpl(get()) }
    single<SetLanguageUseCase> { SetLanguageUseCaseImpl(get()) }
    single<GetLanguageUseCase> { GetLanguageUseCaseImpl(get()) }
    single<GetUnitsUseCase> { GetUnitsUseCaseImpl(get()) }
    single<SetUnitsUseCase> { SetUnitsUseCaseImpl(get()) }
    single<FetchRemoteConfigUseCase> { FetchRemoteConfigUseCaseImpl(get()) }
    single<GetForceUpdateStateUseCase> { GetForceUpdateStateUseCaseImpl(get(), get()) }
    single<GetMinAppCodeUseCase> { GetMinAppCodeUseCaseImpl(get()) }
    single<GetCurrentAppCodeUseCase> { GetCurrentAppCodeUseCaseImpl(get()) }
    single<GetVersionNameUseCase> { GetVersionNameUseCaseImpl(get()) }
    single<GetUserUseCase> { GetUserUseCaseImpl(get()) }
    single<CreateUserUseCase> { CreateUserUseCaseImpl(get()) }
    single<ObserveAuthStateChangedUseCase> { ObserveAuthStateChangedUseCaseImpl(get(), get()) }
    single<ResendVerificationEmailUseCase> { ResendVerificationEmailUseCaseImpl(get()) }
    single<AddExerciseUseCase> { AddExerciseUseCaseImpl(get()) }
    single<AddResultUseCase> { AddResultUseCaseImpl(get(), get()) }
    single<RemoveResultUseCase> { RemoveResultUseCaseImpl(get()) }
    single<DownloadExerciseUseCase> { DownloadExerciseUseCaseImpl(get()) }
    single<ObserveExerciseUseCase> { ObserveExerciseUseCaseImpl(get()) }
    single<SetOfflineModeUseCase> { SetOfflineModeUseCaseImpl(get()) }
    single<GetIsOfflineModeUseCase> { GetIsOfflineModeUseCaseImpl(get()) }
    single<SynchronizeDatabaseUseCase> { SynchronizeDatabaseUseCaseImpl(get()) }
    single<CheckIfSynchronizationIsPossibleUseCase> {
        CheckIfSynchronizationIsPossibleUseCaseImpl(
            get()
        )
    }
    single<FormatMillisecondsToRunningAmountUseCase> { FormatMillisecondsToRunningAmountUseCaseImpl() }
    single<FormatRunningAmountToMillisecondsUseCase> { FormatRunningAmountToMillisecondsUseCaseImpl() }
    single<FormatRunningAmountUseCase> { FormatRunningAmountUseCaseImpl() }
}

val repositoryUseModule = module {
    single { AuthRepository(get()) }
    single { UserRepository(get()) }
    single { CategoryRepository(get(), get(), get(), get(), get(), get()) }
    single { RemoteConfigRepository(get()) }
    single { VersionRepository(get()) }
    single { SessionRepository(get()) }
}

val serviceModule = module {
    single { UserService(get(), get()) }
    single { CategoryService(get(), get(), get(), get()) }
}

val clientModule = module {
    single { KtorClient(get(), get()) }
    single { UserApi(get()) }
    single { CategoryApi(get()) }
    single { EngineProvider() }
}

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
    single {
        NavigationProvider(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single { BottomNavigationProvider(get(), get()) }
}
