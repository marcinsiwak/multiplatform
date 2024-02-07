package pl.msiwak.multiplatform.shared.category

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.ui.category.CategoryViewModel

class CategoryDiHelper(val id: String) : KoinComponent {
    private val categoryVM: CategoryViewModel by inject(parameters = { parametersOf(id) })
    fun getCategoryViewModel() = categoryVM
}
