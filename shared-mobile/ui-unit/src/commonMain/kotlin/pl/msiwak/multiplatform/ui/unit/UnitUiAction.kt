package pl.msiwak.multiplatform.ui.unit

sealed class UnitUiAction {
    data class OnUnitTypeChanged(val pos: Int) : UnitUiAction()
}
