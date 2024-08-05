package pl.msiwak.database.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.upsert
import pl.msiwak.database.table.Auditable
import pl.msiwak.database.table.Categories

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) {
    block()
}

fun <T: Auditable> insertWithAudit(table: T, body: T.(InsertStatement<Number>) -> Unit): InsertStatement<Number> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    val insertStatement = table.insert {
        body(it)
        it[createdAtUtc] = now
        it[modifiedAtUtc] = now
    }
    return insertStatement
}