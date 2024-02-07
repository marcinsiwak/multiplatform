package pl.msiwak.multiplatform.shared.register

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.register.RegisterViewModel

class RegisterDiHelper : KoinComponent {
    private val registerVM: RegisterViewModel by inject()
    fun getViewModel(): RegisterViewModel = registerVM
}
