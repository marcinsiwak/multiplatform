package pl.msiwak.multiplatform.commonObject

import kotlinx.serialization.Serializable

@Serializable
enum class ExerciseType(val unitMetric: String, val unitImperial: String, val convertValue: Double) {
    RUNNING("m", "yd", 1.0936133),
    GYM("kg", "lbs", 2.20462);
//    OTHER("", "", 1.0);

    fun getUnit(unitType: UnitType): String {
        return when(unitType) {
            UnitType.METRIC -> this.unitMetric
            UnitType.IMPERIAL -> this.unitImperial
        }
    }
}