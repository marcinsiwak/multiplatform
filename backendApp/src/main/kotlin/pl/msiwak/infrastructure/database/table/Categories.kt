package pl.msiwak.infrastructure.database.table

object Categories : Auditable() {
    val id = varchar("id", FIELD_VALUE_LENGTH)
    val userId = varchar("userId", FIELD_VALUE_LENGTH)
    val name = varchar("name", FIELD_VALUE_LENGTH)
    val exerciseType = varchar("exerciseType", FIELD_VALUE_LENGTH)

    override val primaryKey = PrimaryKey(id)
}
