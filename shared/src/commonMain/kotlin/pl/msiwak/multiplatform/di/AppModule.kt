package pl.msiwak.multiplatform.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.domain.authorization.LoginUseCase
import pl.msiwak.multiplatform.domain.authorization.RegisterUseCase
import pl.msiwak.multiplatform.ui.login.LoginViewModel
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

fun appModule() = listOf(platformModule, androidModule, useCaseModule)

val androidModule = module {
    single { FirebaseAuthorization() }
    single { Navigator() }
    factory { MainViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { WelcomeScreenViewModel(get()) }
}

val useCaseModule = module {
    single { RegisterUseCase(get()) }
    single { LoginUseCase(get()) }
}
