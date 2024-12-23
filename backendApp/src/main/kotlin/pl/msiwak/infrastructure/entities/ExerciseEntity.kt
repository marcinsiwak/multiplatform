package pl.msiwak.infrastructure.entities

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ExerciseEntity(
    val id: String? = null,
    val categoryId: String? = null,
    val name: String,
    val results: HashSet<ResultEntity> = hashSetOf()
) {
    constructor(name: String, categoryId: String) : this(
        id = UUID.randomUUID().toString(),
        categoryId = categoryId,
        name = name
    )

    fun addResult(resultEntity: ResultEntity) {
        results.add(resultEntity)
    }

    fun removeResult(resultId: String) {
        results.removeIf { it.id == resultId }
    }
}
