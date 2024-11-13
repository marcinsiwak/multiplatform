package pl.msiwak.interfaces.mapper

import pl.msiwak.domain.entities.ExerciseEntity
import pl.msiwak.interfaces.dtos.ExerciseDTO

class ExerciseDTOMapper(
    private val resultDTOMapper: ResultDTOMapper
) : Mapper<Pair<ExerciseEntity, String>, ExerciseDTO>() {
    override fun map(value: Pair<ExerciseEntity, String>): ExerciseDTO = with(value.first) {
        ExerciseDTO(
            id = id,
            categoryId = categoryId!!,
            name = name,
            exerciseType = value.second,
            results = results.map {
                resultDTOMapper(it)
            }
        )
    }
}
