package pl.msiwak.multiplatform.ui.unit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCase

class UnitViewModel(
    private val setUnitsUseCase: SetUnitsUseCase,
    getUnitsUseCase: GetUnitsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(UnitState())
    val viewState: StateFlow<UnitState> = _viewState

    init {
        val units = viewState.value.unitItemList.map {
            if (it.unitType == getUnitsUseCase()) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _viewState.update { it.copy(unitItemList = units) }
    }

    fun onUnitTypeChanged(pos: Int) {
        val newItem = viewState.value.unitItemList.mapIndexed { index, item ->
            if (pos == index) {
                setUnitsUseCase(item.unitType)
                item.copy(isSelected = true)
            } else {
                item.copy(isSelected = false)
            }
        }
        _viewState.update { it.copy(unitItemList = newItem) }
    }
}
