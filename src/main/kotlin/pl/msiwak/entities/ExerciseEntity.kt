package pl.msiwak.entities

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class ExerciseEntity(
    val id: String? = null,
    val categoryId: String? = null,
    val name: String,
    val results: HashSet<ResultEntity> = hashSetOf(),
) {

    constructor(name: String) : this(
        id = UUID.randomUUID().toString(),
        name = name
    )

    fun addResult(resultEntity: ResultEntity) {
        results.add(resultEntity)
    }
}
