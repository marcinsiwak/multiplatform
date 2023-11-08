package pl.msiwak.multiplatform.ui.addExercise

sealed class AddExerciseEvent {
    data object OpenCalendar : AddExerciseEvent()
    class FocusOnInput(val pos: Int) : AddExerciseEvent() {
        fun getArgument(): Int = pos
    }
}
