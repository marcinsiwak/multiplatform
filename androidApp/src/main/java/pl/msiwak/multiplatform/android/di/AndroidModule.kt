package pl.msiwak.multiplatform.android.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.ui.viewModels.WelcomeScreenViewModel
import pl.msiwak.multiplatform.android.ui.MainViewModel
import pl.msiwak.multiplatform.android.ui.login.LoginViewModel
import pl.msiwak.multiplatform.android.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.authorization.FirebaseAuthorization
import pl.msiwak.multiplatform.ui.navigator.Navigator

val androidModule = module {
    single { Navigator() }
    single { FirebaseAuthorization() }
    factory { MainViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { WelcomeScreenViewModel(get()) }
}
