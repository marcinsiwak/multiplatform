package pl.msiwak.multiplatform.ui.addCategory

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCategoryDiHelper : KoinComponent {
    private val addCategoryVM: AddCategoryViewModel by inject()
    fun getViewModel() = addCategoryVM
}