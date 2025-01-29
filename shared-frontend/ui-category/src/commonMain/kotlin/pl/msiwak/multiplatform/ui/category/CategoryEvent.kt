package pl.msiwak.multiplatform.ui.category

sealed class CategoryEvent {
    data class NavigateToAddExercise(val id: String) : CategoryEvent()
}
