package pl.msiwak.multiplatform.android.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.android.ui.MainViewModel
import pl.msiwak.multiplatform.ui.login.LoginViewModel
import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

val androidModule = module {
    single { Navigator() }
    single { FirebaseAuthorization() }
    factory { MainViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { WelcomeScreenViewModel(get()) }
}
