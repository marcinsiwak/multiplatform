package pl.msiwak.multiplatform.ui.addExercise

sealed class AddExerciseEvent {
    object OpenCalendar: pl.msiwak.multiplatform.ui.addExercise.AddExerciseEvent()
    class FocusOnInput(val pos: Int): pl.msiwak.multiplatform.ui.addExercise.AddExerciseEvent() {
        fun getArgument(): Int = pos
    }
}
