package pl.msiwak.multiplatform.ui.register

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterDiHelper : KoinComponent {
    private val registerVM: RegisterViewModel by inject()
    fun getViewModel(): RegisterViewModel = registerVM
}
