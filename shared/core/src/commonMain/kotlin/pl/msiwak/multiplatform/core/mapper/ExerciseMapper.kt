package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.api.data.user.ApiExercise

class ExerciseMapper : Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            value.exerciseId,
            value.name
        )
    }
}