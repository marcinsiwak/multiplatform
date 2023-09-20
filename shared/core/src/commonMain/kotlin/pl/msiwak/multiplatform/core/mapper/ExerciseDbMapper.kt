package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiExercise
import pl.msiwak.multiplatform.core.data.entity.ExerciseEntity

class ExerciseDbMapper() : Mapper<ApiExercise, ExerciseEntity>() {

    override fun map(value: ApiExercise): ExerciseEntity {
        return ExerciseEntity(
            id = value.exerciseId,
            exerciseTitle = value.name,
        )
    }
}