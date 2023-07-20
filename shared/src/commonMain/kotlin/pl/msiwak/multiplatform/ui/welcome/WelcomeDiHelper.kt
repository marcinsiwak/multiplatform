package pl.msiwak.multiplatform.ui.welcome

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WelcomeDiHelper : KoinComponent {
    private val welcomeVM: WelcomeScreenViewModel by inject()
    fun getViewModel() = welcomeVM
}