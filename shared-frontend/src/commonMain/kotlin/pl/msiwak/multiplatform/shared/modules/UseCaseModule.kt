package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCase
import pl.msiwak.multiplatform.domain.authorization.CheckIfSynchronizationIsPossibleUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCaseImpl
import pl.msiwak.multiplatform.domain.authorization.LoginWithProviderUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginWithProviderUseCaseImpl
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
import pl.msiwak.multiplatform.domain.user.GetUsersUseCase
import pl.msiwak.multiplatform.domain.user.GetUsersUseCaseImpl
import pl.msiwak.multiplatform.domain.user.SendNotificationUseCase
import pl.msiwak.multiplatform.domain.user.SendNotificationUseCaseImpl
import pl.msiwak.multiplatform.domain.user.UpdateUserUseCase
import pl.msiwak.multiplatform.domain.user.UpdateUserUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCaseImpl
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCase
import pl.msiwak.multiplatform.domain.version.GetVersionNameUseCaseImpl

val useCaseModule = module {
    single<RegisterUserUseCase> { RegisterUserUseCaseImpl(get(), get()) }
    single<LoginUseCase> { LoginUseCaseImpl(get(), get(), get()) }
    single<LoginWithProviderUseCase> { LoginWithProviderUseCaseImpl(get(), get(), get()) }
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
    single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    single<CreateUserUseCase> { CreateUserUseCaseImpl(get()) }
    single<UpdateUserUseCase> { UpdateUserUseCaseImpl(get()) }
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
    single<CheckIfSynchronizationIsPossibleUseCase> { CheckIfSynchronizationIsPossibleUseCaseImpl(get()) }
    single<FormatMillisecondsToRunningAmountUseCase> { FormatMillisecondsToRunningAmountUseCaseImpl() }
    single<FormatRunningAmountToMillisecondsUseCase> { FormatRunningAmountToMillisecondsUseCaseImpl() }
    single<FormatRunningAmountUseCase> { FormatRunningAmountUseCaseImpl() }
    single<SendNotificationUseCase> { SendNotificationUseCaseImpl(get()) }
}