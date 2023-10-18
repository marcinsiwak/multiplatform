package pl.msiwak.multiplatform.network.mapper

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiExercise

class ExerciseMapper(private val resultMapper: ResultMapper) : Mapper<ApiExercise, Exercise>() {

    override fun map(value: ApiExercise): Exercise {
        return Exercise(
            id = value.exerciseId,
            categoryId = value.categoryId,
            exerciseTitle = value.name,
            results = value.results.map { resultMapper(it) },
            creationDate = value.creationDate.toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }
}