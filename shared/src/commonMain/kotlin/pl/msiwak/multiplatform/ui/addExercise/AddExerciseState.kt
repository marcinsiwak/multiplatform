package pl.msiwak.multiplatform.ui.addExercise

data class AddExerciseState(
    val exerciseTitle: String = "",
    val newResult: String = "",
    val results: List<String> = listOf(),
)