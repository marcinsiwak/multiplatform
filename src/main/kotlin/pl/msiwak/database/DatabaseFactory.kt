package pl.msiwak.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import pl.msiwak.database.table.Categories
import pl.msiwak.database.table.Users

object DatabaseFactory {
    fun init() {
        // todo move to env vars
        val database = Database.connect(
            url = "jdbc:postgresql://localhost:5432/postgres",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "postgres"
        )
        transaction(database) {
            SchemaUtils.create(Users)
            SchemaUtils.create(Categories)
        }
    }
}