package pl.msiwak.database.table

object Exercises : Auditable() {
    val id = varchar("id", 128)
    val categoryId = varchar("categoryId", 128)
    val name = varchar("name", 128)

    override val primaryKey = PrimaryKey(id)
}
