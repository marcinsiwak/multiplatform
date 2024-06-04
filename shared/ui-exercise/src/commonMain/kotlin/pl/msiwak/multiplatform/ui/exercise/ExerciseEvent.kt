package pl.msiwak.multiplatform.ui.exercise

sealed class ExerciseEvent {
    class FocusOnInput(val pos: Int) : ExerciseEvent()
}
