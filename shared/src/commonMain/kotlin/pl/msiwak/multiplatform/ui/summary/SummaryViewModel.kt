package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.domain.summaries.GetCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateCategoriesUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SummaryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val updateCategoriesUseCase: UpdateCategoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryState())
    val summaryState: StateFlow<SummaryState> = _summaryState

    fun onInit() {
        viewModelScope.launch {
            updateCategoriesUseCase(
                CategoryData(
                    id =1,
                    "Gym",
                    emptyList(),
                    ExerciseType.GYM
                )
            )
            updateCategoriesUseCase(
                CategoryData(
                    2,
                    "Running",
                    emptyList(),
                    ExerciseType.RUNNING
                )
            )
            val categories = getCategoriesUseCase()
            _summaryState.value = _summaryState.value.copy(categories = categories)
        }
    }

    fun onAddExerciseClicked() {
        navigator.navigate(NavigationDirections.AddExercise)
    }

    fun onExerciseClicked(id: Long) {
        navigator.navigate(NavigationDirections.Category(id))
    }
}