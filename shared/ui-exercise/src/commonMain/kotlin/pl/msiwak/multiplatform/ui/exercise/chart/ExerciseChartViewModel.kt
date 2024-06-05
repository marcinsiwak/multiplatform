package pl.msiwak.multiplatform.ui.exercise.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.domain.summaries.GetResultsUseCase

class ExerciseChartViewModel(
    private val getResultsUseCase: GetResultsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ExerciseChartState())
    val viewState: StateFlow<ExerciseChartState> = _viewState.asStateFlow()

    fun onInit(exerciseId: String, exerciseType: ExerciseType) {
        viewModelScope.launch {
            val results = getResultsUseCase(exerciseId = exerciseId, exerciseType = exerciseType)
            _viewState.update { it.copy(results = results) }
        }
    }
}
