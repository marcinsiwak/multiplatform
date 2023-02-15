package pl.msiwak.multiplatform.ui.exercise

data class ExerciseState(
    val exerciseTitle: String = "",
    val newResult: String = "",
    val results: List<String> = listOf(),
)