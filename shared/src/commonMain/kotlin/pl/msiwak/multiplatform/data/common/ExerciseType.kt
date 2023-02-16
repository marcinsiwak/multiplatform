package pl.msiwak.multiplatform.data.common

sealed class ExerciseType {
    class Running: ExerciseType()
    class Gym(): ExerciseType()
}