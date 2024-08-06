package pl.msiwak.database.table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Results : Auditable() {
    val id = varchar("id", 128)
    val exerciseId = varchar("exerciseId", 128)
    val amount = varchar("amount", 128)
    val result = varchar("result", 128)
    val date = datetime("date")

    override val primaryKey = PrimaryKey(id)
}
