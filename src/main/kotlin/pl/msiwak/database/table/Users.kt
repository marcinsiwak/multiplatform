package pl.msiwak.database.table

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 128)
    val email = varchar("email", 128)

    override val primaryKey = PrimaryKey(id)
}