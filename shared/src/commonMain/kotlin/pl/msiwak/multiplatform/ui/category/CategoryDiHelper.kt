package pl.msiwak.multiplatform.ui.category

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoryDiHelper : KoinComponent {
    private val categoryVM: CategoryViewModel by inject()
    fun getCategoryViewModel() = categoryVM
}