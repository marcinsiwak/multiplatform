package pl.msiwak.multiplatform.shared.main

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.shared.MainViewModel

class MainDiHelper : KoinComponent {
    private val mainVM: MainViewModel by inject()
    fun getMainViewModel(): MainViewModel = mainVM
}