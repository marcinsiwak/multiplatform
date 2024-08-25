package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Results : Auditable() {
    val id = varchar("id", 128)
    val exerciseId = reference("exerciseId", Exercises.id, onDelete = ReferenceOption.CASCADE)
    val amount = varchar("amount", 128)
    val result = varchar("result", 128)
    val date = datetime("date")

    override val primaryKey = PrimaryKey(id)
}
