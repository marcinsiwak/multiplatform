package pl.msiwak.entities

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class ExerciseEntity(
    val id: String? = null,
    val categoryId: String? = null,
    val name: String,
//    @Serializable(with = LocalDateTimeSerializer::class)
//    val creationDate: LocalDateTime
) {

    constructor(name: String) : this(
        id = UUID.randomUUID().toString(),
        name = name
    )

}
