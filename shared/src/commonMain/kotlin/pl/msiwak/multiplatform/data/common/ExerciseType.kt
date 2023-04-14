package pl.msiwak.multiplatform.data.common

@kotlinx.serialization.Serializable
enum class ExerciseType(val unitMetric: String, val unitImperial: String, val convertValue: Double) {
    RUNNING("m", "lbs", 39.3701),
    GYM("kg", "lbs", 2.20462),
//    OTHER("", "", 1.0);
}