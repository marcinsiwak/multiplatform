package pl.msiwak.infrastructure.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ResultEntity(
    val id: String? = null,
    val exerciseId: String? = null,
    val amount: String,
    val result: String,
    val date: Instant
) {
    constructor(
        exerciseId: String,
        amount: String,
        result: String,
        date: Instant
    ) : this(
        id = UUID.randomUUID().toString(),
        exerciseId = exerciseId,
        amount = amount,
        result = result,
        date = date
    )
}
