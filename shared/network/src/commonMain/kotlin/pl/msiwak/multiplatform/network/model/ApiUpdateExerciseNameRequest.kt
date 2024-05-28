package pl.msiwak.multiplatform.network.model

@Serializable
class ApiUpdateExerciseNameRequest(
    val exerciseId: String,
    val name: String
)
