package pl.msiwak.multiplatform.core.ui.verifyEmail

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VerifyEmailDiHelper : KoinComponent {
    private val verifyVM: VerifyEmailViewModel by inject()
    fun getViewModel() = verifyVM
}