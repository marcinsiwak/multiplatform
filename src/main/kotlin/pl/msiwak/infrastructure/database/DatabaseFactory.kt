package pl.msiwak.infrastructure.database

import io.ktor.server.config.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import pl.msiwak.infrastructure.database.table.Categories
import pl.msiwak.infrastructure.database.table.Exercises
import pl.msiwak.infrastructure.database.table.Results
import pl.msiwak.infrastructure.database.table.Users
import java.io.File

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

        val flyway = Flyway.configure().dataSource(url, user, password).load()
        flyway.clean()
        flyway.migrate()

        transaction(database) {
            SchemaUtils.create(Users, Categories, Exercises, Results)
        }
    }

    // function example for migration
    fun generateAlterTableScript() {
        val outputFile = File("src/main/resources/db/migration/V2__Add_Age_Column.sql")

        transaction {
//            val sqlStatements = SchemaUtils.createMissingTablesAndColumns(Users, Categories, Exercises, Results)
            val sqlStatements =SchemaUtils.statementsRequiredForDatabaseMigration()

            outputFile.writeText(sqlStatements.joinToString(";\n") + ";")
        }
    }
}