package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.core.api.data.user.ApiExercise
import pl.msiwak.multiplatform.data.common.Exercise

class ExerciseMapper: Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            value.exerciseId,
            value.name
        )
    }
}