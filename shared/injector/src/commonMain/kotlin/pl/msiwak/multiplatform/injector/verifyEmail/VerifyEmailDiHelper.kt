package pl.msiwak.multiplatform.injector.verifyEmail

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel

class VerifyEmailDiHelper : KoinComponent {
    private val verifyVM: VerifyEmailViewModel by inject()
    fun getViewModel() = verifyVM
}