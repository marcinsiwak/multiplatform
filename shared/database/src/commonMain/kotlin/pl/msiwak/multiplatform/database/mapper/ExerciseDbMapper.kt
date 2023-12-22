package pl.msiwak.multiplatform.database.mapper

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.database.model.ExerciseEntity

class ExerciseDbMapper() : Mapper<Exercise, ExerciseEntity>() {

    override fun map(value: Exercise): ExerciseEntity {
        return ExerciseEntity(
            id = value.id,
            exerciseTitle = value.exerciseTitle
        )
    }
}
