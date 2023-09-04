package pl.msiwak.multiplatform.core.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.core.domain.summaries.DownloadCategoriesUseCase
import pl.msiwak.multiplatform.core.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.core.ui.navigator.Navigator

class SummaryViewModel(
    private val downloadCategoriesUseCase: DownloadCategoriesUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(SummaryState())
    val viewState: StateFlow<SummaryState> = _viewState

    init {
        viewModelScope.launch {
            downloadCategoriesUseCase()
            observeCategoriesUseCase().collect {
                _viewState.value = _viewState.value.copy(categories = it)
            }
        }
    }

    fun onAddCategoryClicked() {
        navigator.navigate(NavigationDirections.AddCategory)
    }

    fun onCategoryClicked(id: String) {
        navigator.navigate(NavigationDirections.CategoryDetails(id))
    }
}