package pl.msiwak.multiplatform.shared.welcome

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

class WelcomeDiHelper : KoinComponent {
    private val welcomeVM: WelcomeScreenViewModel by inject()
    fun getViewModel() = welcomeVM
}
