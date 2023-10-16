package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator
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
    private val categories: MutableList<Category> = mutableListOf()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            observeCategoriesUseCase().collect {
                categories.clear()
                categories.addAll(it)
                _viewState.value = _viewState.value.copy(categories = it)
            }
        }
    }

    fun onResume() {
        viewModelScope.launch(errorHandler) {
            downloadCategoriesUseCase()
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
        _viewState.value = _viewState.value.copy(isRemoveCategoryDialogVisible = true)
    }

    fun onCategoryRemoved() {
        categoryToRemovePosition?.let {
            val id = categories[it].id
            categories.removeAt(it)
            _viewState.value = _viewState.value.copy(categories = categories)

            viewModelScope.launch {
                removeCategoryUseCase(id)
                _viewState.value = _viewState.value.copy(isRemoveCategoryDialogVisible = false)
            }
        }
    }

    fun onPopupDismissed() {
        _viewState.value = _viewState.value.copy(isRemoveCategoryDialogVisible = false)
    }
}