package pl.msiwak.infrastructure.database.table

object Users : Auditable() {
    val id = varchar("id", 128)
    val name = varchar("name", 128)
    val email = varchar("email", 128)

    override val primaryKey = PrimaryKey(id)
}