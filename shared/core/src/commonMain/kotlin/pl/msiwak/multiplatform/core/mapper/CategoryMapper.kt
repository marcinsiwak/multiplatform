package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.core.api.data.user.ApiCategory

class CategoryMapper(private val exerciseMapper: ExerciseMapper) : Mapper<ApiCategory, Category>() {

    override fun map(value: ApiCategory): Category {
//        val exercises = value.exercises.map { exerciseMapper(it) }
        return Category(value.categoryId, value.name, ExerciseType.valueOf(value.exerciseType))
    }
}