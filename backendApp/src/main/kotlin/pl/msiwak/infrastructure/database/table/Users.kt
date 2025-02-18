package pl.msiwak.infrastructure.database.table

object Users : Auditable() {
    val id = varchar("id", FIELD_VALUE_LENGTH)
    val name = varchar("name", FIELD_VALUE_LENGTH)
    val email = varchar("email", FIELD_VALUE_LENGTH)
    val role = varchar("role", FIELD_VALUE_LENGTH)
    val deviceToken = varchar("deviceToken", TOKEN_VALUE_LENGTH).nullable()

    override val primaryKey = PrimaryKey(id)
}
