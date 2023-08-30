package pl.msiwak.multiplatform.mapper

import pl.msiwak.multiplatform.api.data.user.ApiExercise
import pl.msiwak.multiplatform.data.entity.ExerciseEntity

class ExerciseDbMapper() : Mapper<ApiExercise, ExerciseEntity>() {

    override fun map(value: ApiExercise): ExerciseEntity {
        return ExerciseEntity(
            id = value.exerciseId,
            exerciseTitle = value.name,
        )
    }
}