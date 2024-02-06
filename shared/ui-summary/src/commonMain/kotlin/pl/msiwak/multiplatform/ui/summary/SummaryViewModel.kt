package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class SummaryViewModel(
    private val downloadCategoriesUseCase: DownloadCategoriesUseCase,
    observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val navigator: Navigator,
    globalErrorHandler: GlobalErrorHandler,
    private val removeCategoryUseCase: RemoveCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SummaryState())
    val viewState: StateFlow<SummaryState> = _viewState

    private var categoryToRemovePosition: Int? = null
    private val currentCategories: MutableList<Category> = mutableListOf()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            observeCategoriesUseCase().collect { categories ->
                currentCategories.clear()
                currentCategories.addAll(categories)
                _viewState.update { it.copy(categories = categories) }
            }
        }
    }

    fun onResume() {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            downloadCategoriesUseCase()
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onAddCategoryClicked() {
        navigator.navigate(NavigationDirections.AddCategory)
    }

    fun onCategoryClicked(id: String) {
        navigator.navigate(NavigationDirections.CategoryDetails(id))
    }

    fun onCategoryLongClicked(categoryIndex: Int) {
        categoryToRemovePosition = categoryIndex
        _viewState.update { it.copy(isRemoveCategoryDialogVisible = true) }
    }

    fun onCategoryRemoved() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }
            categoryToRemovePosition?.let { pos ->
                val id = currentCategories[pos].id
                currentCategories.removeAt(pos)
                _viewState.update { it.copy(categories = currentCategories) }

                removeCategoryUseCase(id)
                _viewState.update { it.copy(isRemoveCategoryDialogVisible = false) }
            }
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onPopupDismissed() {
        _viewState.update { it.copy(isRemoveCategoryDialogVisible = false) }
    }
}
