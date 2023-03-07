package pl.msiwak.multiplatform.data.common

@kotlinx.serialization.Serializable
enum class ExerciseType(name: String) {
    RUNNING("running"),
    GYM("gym");
}