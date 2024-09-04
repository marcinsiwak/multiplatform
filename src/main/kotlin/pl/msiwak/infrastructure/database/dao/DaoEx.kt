package pl.msiwak.infrastructure.database.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.statements.UpsertStatement
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.upsert
import pl.msiwak.infrastructure.database.table.Auditable

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) {
    block()
}

fun <T : Auditable> upsertWithAudit(table: T, body: T.(UpsertStatement<Long>) -> Unit): UpsertStatement<Long> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    val upsertStatement = table.upsert(onUpdateExclude = listOf(table.createdAtUtc))
    {
        body(it)
        it[createdAtUtc] = now
        it[modifiedAtUtc] = now
    }
    return upsertStatement
}
