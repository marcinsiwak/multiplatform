package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.shared.model.ApiCategory

class CategoryMapper(private val exerciseMapper: ExerciseMapper) : Mapper<ApiCategory, Category>() {

    override fun map(value: ApiCategory): Category {
        val exercises = value.exercises.map { exerciseMapper(it) }
        return Category(
            id = value.id!!,
            name = value.name,
            exerciseType = ExerciseType.valueOf(value.exerciseType),
            exercises = exercises
        )
    }
}
