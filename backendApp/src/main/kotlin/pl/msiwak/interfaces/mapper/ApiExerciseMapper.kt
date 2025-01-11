package pl.msiwak.interfaces.mapper

import pl.msiwak.infrastructure.entities.ExerciseEntity
import pl.msiwak.multiplatform.shared.model.ApiExercise

class ApiExerciseMapper(
    private val apiResultMapper: ApiResultMapper
) : Mapper<Pair<ExerciseEntity, String>, ApiExercise>() {
    override fun map(value: Pair<ExerciseEntity, String>): ApiExercise = with(value.first) {
        ApiExercise(
            id = id,
            categoryId = categoryId!!,
            name = name,
            exerciseType = value.second,
            results = results.map {
                apiResultMapper(it)
            }
        )
    }
}
