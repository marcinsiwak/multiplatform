package pl.msiwak.multiplatform.ui.addExercise

import pl.msiwak.multiplatform.commonObject.DateFilterType

sealed class AddExerciseUiAction {
    data object OnResultRemoved : AddExerciseUiAction()
    data object OnPopupDismissed : AddExerciseUiAction()
    data class OnConfirmRunningAmount(val hours: String, val minutes: String, val seconds: String, val milliseconds: String) : AddExerciseUiAction()
    data object OnDismissAmountDialog : AddExerciseUiAction()
    data class OnTabClicked(val dateFilterType: DateFilterType) : AddExerciseUiAction()
    data object OnSaveResultClicked : AddExerciseUiAction()
    data object OnAddNewResultClicked : AddExerciseUiAction()
    data class OnResultValueChanged(val result: String) : AddExerciseUiAction()
    data class OnAmountValueChanged(val amount: String) : AddExerciseUiAction()
    data class OnDateValueChanged(val date: String) : AddExerciseUiAction()
    data object OnDateClicked : AddExerciseUiAction()
    data class OnResultLongClicked(val pos: Int) : AddExerciseUiAction()
    data class OnLabelClicked(val index: Int) : AddExerciseUiAction()
    data object OnAmountClicked : AddExerciseUiAction()
    data class OnDateConfirmClicked(val selectedDateMillis: Long?) : AddExerciseUiAction()
    data object OnDateDismiss : AddExerciseUiAction()
}
