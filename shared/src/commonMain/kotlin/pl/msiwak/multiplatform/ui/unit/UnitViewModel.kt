package pl.msiwak.multiplatform.ui.unit

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.settings.SetUnitsUseCase

class UnitViewModel(
    private val setUnitsUseCase: SetUnitsUseCase,
    getUnitsUseCase: GetUnitsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(UnitState())
    val viewState: StateFlow<UnitState> = _viewState

    init {
        val unit = getUnitsUseCase()
        val units = _viewState.value.unitItemList.map {
            if (it.unitType == unit) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _viewState.value = _viewState.value.copy(unitItemList = units)
    }

    fun onUnitTypeChanged(pos: Int) {
        val newItem = _viewState.value.unitItemList.mapIndexed { index, item ->
            if (pos == index) {
                setUnitsUseCase(item.unitType)
                item.copy(isSelected = true)
            } else {
                item.copy(isSelected = false)
            }
        }
        _viewState.value = _viewState.value.copy(unitItemList = newItem)
    }
}