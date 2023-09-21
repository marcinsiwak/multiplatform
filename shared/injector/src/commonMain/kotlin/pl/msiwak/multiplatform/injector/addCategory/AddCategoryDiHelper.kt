package pl.msiwak.multiplatform.injector.addCategory

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel

class AddCategoryDiHelper : KoinComponent {
    private val addCategoryVM: AddCategoryViewModel by inject()
    fun getViewModel() = addCategoryVM
}