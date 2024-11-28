package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

abstract class Auditable : Table() {
    val createdAtUtc = timestamp("created_at_utc").defaultExpression(CurrentTimestamp)
    val modifiedAtUtc = timestamp("modified_at_utc").defaultExpression(CurrentTimestamp)
}
