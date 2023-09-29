package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiExercise

class ExerciseMapper : Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            id = value.exerciseId,
            categoryId = value.categoryId,
            exerciseTitle = value.name
        )
    }
}