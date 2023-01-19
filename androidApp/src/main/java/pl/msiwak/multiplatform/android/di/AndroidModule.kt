package pl.msiwak.multiplatform.android.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.android.navigation.Navigator
import pl.msiwak.multiplatform.android.ui.MainViewModel
import pl.msiwak.multiplatform.android.ui.register.RegisterViewModel
import pl.msiwak.multiplatform.authorization.FirebaseAuthorization

val androidModule = module {
    single { Navigator() }
    single { FirebaseAuthorization() }
    factory { MainViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { WelcomeScreenViewModel(get()) }
}
