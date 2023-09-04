package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.core.api.data.user.ApiCategory
import pl.msiwak.multiplatform.core.data.common.ExerciseType
import pl.msiwak.multiplatform.core.data.entity.CategoryEntity

class CategoryDbMapper(private val exerciseMapper: ExerciseMapper) :
    Mapper<ApiCategory, CategoryEntity>() {

    override fun map(value: ApiCategory): CategoryEntity {
//        val exercises = value.exercises.map { exerciseMapper(it) }
        return CategoryEntity(
            id = value.categoryId,
            name = value.name,
            exerciseType = ExerciseType.valueOf(value.exerciseType)
        )
    }
}