package pl.msiwak.interfaces.mapper

import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.interfaces.dtos.CategoryDTO

class CategoryDTOMapper(
    private val exerciseDTOMapper: ExerciseDTOMapper
) : Mapper<CategoryEntity, CategoryDTO>() {
    override fun map(value: CategoryEntity): CategoryDTO = with(value) {
        CategoryDTO(
            id = id,
            name = name,
            exerciseType = exerciseType,
            exercises = exercises.map {
                exerciseDTOMapper(Pair(it, exerciseType))
            }
        )
    }
}
