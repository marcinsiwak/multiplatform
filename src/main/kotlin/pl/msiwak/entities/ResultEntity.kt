package pl.msiwak.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ResultEntity(
    val id: String? = null,
    val amount: String,
    val result: String,
    val date: LocalDateTime
) {
    constructor(
        amount: String,
        result: String,
        date: LocalDateTime
    ) : this(
        id = UUID.randomUUID().toString(),
        amount = amount,
        result = result,
        date = date
    )
}
