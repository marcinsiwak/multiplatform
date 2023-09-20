package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.network.model.ApiCategory
import pl.msiwak.multiplatform.core.data.entity.CategoryEntity
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper

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