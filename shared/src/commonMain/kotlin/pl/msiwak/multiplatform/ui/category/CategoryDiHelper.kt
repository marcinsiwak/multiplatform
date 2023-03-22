package pl.msiwak.multiplatform.ui.category

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class CategoryDiHelper(val id: Long) : KoinComponent {
    private val categoryVM: CategoryViewModel by inject(parameters = { parametersOf(id) })
    fun getCategoryViewModel() = categoryVM
}