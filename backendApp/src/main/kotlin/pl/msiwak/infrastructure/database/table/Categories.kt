package pl.msiwak.infrastructure.database.table

object Categories : Auditable() {
    val id = varchar("id", 128)
    val userId = varchar("userId", 128)
    val name = varchar("name", 128)
    val exerciseType = varchar("exerciseType", 128)

    override val primaryKey = PrimaryKey(id)
}
