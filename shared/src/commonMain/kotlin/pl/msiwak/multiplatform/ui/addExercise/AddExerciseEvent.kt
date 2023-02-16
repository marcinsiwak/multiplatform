package pl.msiwak.multiplatform.ui.addExercise

sealed class AddExerciseEvent {
    object OpenCalendar: AddExerciseEvent()
}
