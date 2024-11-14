package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.ReferenceOption

object Exercises : Auditable() {
    val id = varchar("id", 128)
    val categoryId = reference("categoryId", Categories.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 128)

    override val primaryKey = PrimaryKey(id)
}
