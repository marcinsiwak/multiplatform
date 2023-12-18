package pl.msiwak.multiplatform.database.mapper

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.base.Mapper
import pl.msiwak.multiplatform.database.model.CategoryEntity

class CategoryDbMapper(private val exerciseMapper: ExerciseDbMapper) :
    Mapper<Category, CategoryEntity>() {

    override fun map(value: Category): CategoryEntity {
        val exercises = value.exercises.map { exerciseMapper(it) }
        return CategoryEntity(
            id = value.id,
            name = value.name,
            exerciseType = value.exerciseType,
            exercises = exercises
        )
    }
}
