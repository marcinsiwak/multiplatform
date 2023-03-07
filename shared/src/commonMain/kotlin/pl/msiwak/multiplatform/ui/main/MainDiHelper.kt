package pl.msiwak.multiplatform.ui.main

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainDiHelper : KoinComponent {
    private val mainVM: MainViewModel by inject()
    fun getMainViewModel(): MainViewModel = mainVM
    fun getNavigator() = mainVM.mainNavigator
}