package pl.msiwak.multiplatform.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

class DiHelper : KoinComponent {
    private val welcomeViewModel: WelcomeScreenViewModel by inject()
    private val mVM: MainViewModel by inject()
    fun getMainViewModel(): MainViewModel = mVM
    fun getNavigator() = mVM.mainNavigator
    fun onRegistrationClicked() = welcomeViewModel.onRegistrationClicked()
    fun onLoginClicked() = welcomeViewModel.onLoginClicked()
}