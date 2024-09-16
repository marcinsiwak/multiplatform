package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.network.model.ApiExercise

class ExerciseMapper(private val resultMapper: ResultMapper) : Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            id = value.id!!,
            categoryId = value.categoryId,
            exerciseTitle = value.name,
            exerciseType = ExerciseType.valueOf(value.exerciseType),
            results = value.results.map { resultMapper(it) }
        )
    }
}
