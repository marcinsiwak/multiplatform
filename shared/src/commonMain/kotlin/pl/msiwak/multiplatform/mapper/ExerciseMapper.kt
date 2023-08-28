package pl.msiwak.multiplatform.mapper

import pl.msiwak.multiplatform.api.data.user.ApiExercise
import pl.msiwak.multiplatform.data.common.Exercise

class ExerciseMapper: Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            value.exerciseId,
            value.name
        )
    }
}