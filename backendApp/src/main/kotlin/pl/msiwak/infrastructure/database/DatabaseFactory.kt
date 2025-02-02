package pl.msiwak.infrastructure.database

import io.ktor.server.config.ApplicationConfig
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import pl.msiwak.infrastructure.database.table.Categories
import pl.msiwak.infrastructure.database.table.Exercises
import pl.msiwak.infrastructure.database.table.Results
import pl.msiwak.infrastructure.database.table.Users

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val url = config.property("storage.jdbcURL").getString()
        val user = config.property("storage.user").getString()
        val password = config.property("storage.password").getString()

        Flyway.configure().baselineOnMigrate(true).dataSource(url, user, password).load().also {
            it.migrate()
        }

        val database = Database.connect(
            url = url,
            user = user,
            password = password
        )

        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(Users, Categories, Exercises, Results)
        }
    }
}
