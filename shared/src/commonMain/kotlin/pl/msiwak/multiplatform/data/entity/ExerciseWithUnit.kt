package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.Exercise

data class ExerciseWithUnit(
    val exercise: Exercise?,
    val unit: String?
)