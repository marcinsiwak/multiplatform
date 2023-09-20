package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiCategory

class CategoryMapper(private val exerciseMapper: ExerciseMapper) : Mapper<ApiCategory, Category>() {

    override fun map(value: ApiCategory): Category {
//        val exercises = value.exercises.map { exerciseMapper(it) }
        return Category(value.categoryId, value.name, ExerciseType.valueOf(value.exerciseType))
    }
}