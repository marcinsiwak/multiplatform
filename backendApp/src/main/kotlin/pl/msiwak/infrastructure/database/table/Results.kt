package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Results : Auditable() {
    val id = varchar("id", FIELD_VALUE_LENGTH)
    val exerciseId = reference("exerciseId", Exercises.id, onDelete = ReferenceOption.CASCADE)
    val amount = varchar("amount", FIELD_VALUE_LENGTH)
    val result = varchar("result", FIELD_VALUE_LENGTH)
    val date = timestamp("date")

    override val primaryKey = PrimaryKey(id)
}
