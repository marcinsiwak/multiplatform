package pl.msiwak.multiplatform.core.ui.addExercise

sealed class AddExerciseEvent {
    object OpenCalendar: AddExerciseEvent()
    class FocusOnInput(val pos: Int): AddExerciseEvent() {
        fun getArgument(): Int = pos
    }
}
