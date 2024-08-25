package pl.msiwak.infrastructure.database.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpsertStatement
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.upsert
import pl.msiwak.infrastructure.database.table.Auditable

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) {
    block()
}

fun <T : Auditable> insertWithAudit(table: T, body: T.(InsertStatement<Number>) -> Unit): InsertStatement<Number> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    val insertStatement = table.insert {
        body(it)
        it[createdAtUtc] = now
        it[modifiedAtUtc] = now
    }
    return insertStatement
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

//fun <T : Auditable> batchUpsertWithAudit(table: T, body: T.(UpsertStatement<Long>) -> Unit, entities: List<T>) {
//    table.batchUpsert(entities) { entity  ->
//        body(entity)
//        this[table.createdAtUtc] = entity.createdAtUtc
//        this[table.modifiedAtUtc] = entity.modifiedAtUtc
//
//        // Map other entity fields as needed
//        // Example: this[table.someField] = entity.someField
//    }
//}
