package pl.msiwak.interfaces.mapper

import pl.msiwak.infrastructure.entities.CategoryEntity
import pl.msiwak.multiplatform.shared.model.ApiCategory

class ApiCategoryMapper(
    private val apiExerciseMapper: ApiExerciseMapper
) : Mapper<CategoryEntity, ApiCategory>() {
    override fun map(value: CategoryEntity): ApiCategory = with(value) {
        ApiCategory(
            id = id,
            name = name,
            exerciseType = exerciseType,
            exercises = exercises.map {
                apiExerciseMapper(Pair(it, exerciseType))
            }
        )
    }
}
