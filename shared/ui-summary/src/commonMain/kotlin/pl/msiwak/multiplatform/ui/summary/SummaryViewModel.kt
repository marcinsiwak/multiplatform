package pl.msiwak.multiplatform.ui.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class SummaryViewModel(
    private val downloadCategoriesUseCase: DownloadCategoriesUseCase,
    observeCategoriesUseCase: ObserveCategoriesUseCase,
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
