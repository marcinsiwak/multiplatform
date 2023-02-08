package pl.msiwak.multiplatform.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.RegisterUserUseCase
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCase
import pl.msiwak.multiplatform.domain.summaries.GetSummariesUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummariesUseCase
import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.repository.SummaryRepository
import pl.msiwak.multiplatform.ui.login.LoginViewModel
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.validators.Validator

fun appModule() = listOf(
    platformModule,
    apiModule,
    viewModelsModule,
    databaseModule,
    useCaseModule,
    toolsModule,
    repositoryUseModule,
)

val databaseModule = module {
    single { Database(get()) }
}

val apiModule = module {
    single { FirebaseAuthorization() }
}

val toolsModule = module {
    single { Navigator() }
    single { Validator() }
    single { GlobalErrorHandler() }
}

val viewModelsModule = module {
    factory { MainViewModel(get()) }
    factory { RegisterViewModel(get(), get(), get()) }
    factory { LoginViewModel(get(), get(), get()) }
    factory { WelcomeScreenViewModel(get()) }
    factory { SummaryViewModel(get(), get()) }
}

val useCaseModule = module {
    single { RegisterUserUseCase(get()) }
    single { LoginUseCase(get()) }
    single { SaveUserTokenUseCase(get()) }
    single { GetUserTokenUseCase(get()) }
    single { GetSummariesUseCase(get()) }
    single { InsertSummariesUseCase(get()) }
}

val repositoryUseModule = module {
    single { AuthRepository(get()) }
    single { SummaryRepository(get()) }
}
