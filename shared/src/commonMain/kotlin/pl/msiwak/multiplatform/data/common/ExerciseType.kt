package pl.msiwak.multiplatform.data.common

sealed class ExerciseType(val backgroundId: Int) {
    class Running(): ExerciseType()
    class Gym(): ExerciseType()
}