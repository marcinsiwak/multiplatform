package pl.msiwak.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ResultEntity(
    val id: String? = null,
    val exerciseId: String? = null,
    val amount: String,
    val result: String,
    val date: LocalDateTime
) {
    constructor(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    ) : this(
        id = UUID.randomUUID().toString(),
        exerciseId = exerciseId,
        amount = amount,
        result = result,
        date = date
    )
}
