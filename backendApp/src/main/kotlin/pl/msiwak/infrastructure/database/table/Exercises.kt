package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.ReferenceOption

object Exercises : Auditable() {
    val id = varchar("id", FIELD_VALUE_LENGTH)
    val categoryId = reference("categoryId", Categories.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", FIELD_VALUE_LENGTH)

    override val primaryKey = PrimaryKey(id)
}
