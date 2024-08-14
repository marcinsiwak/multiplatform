package pl.msiwak.database

import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import pl.msiwak.database.table.Categories
import pl.msiwak.database.table.Exercises
import pl.msiwak.database.table.Results
import pl.msiwak.database.table.Users

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val url = config.property("storage.jdbcURL").getString()
        val user = config.property("storage.user").getString()
        val password = config.property("storage.password").getString()

        val database = Database.connect(
            url = url,
            user = user,
            password = password
        )

        transaction(database) {
            SchemaUtils.create(Users, Categories, Exercises, Results)
        }
    }
}