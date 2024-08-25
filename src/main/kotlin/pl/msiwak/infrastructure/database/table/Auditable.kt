package pl.msiwak.infrastructure.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

abstract class Auditable : Table() {
    val createdAtUtc = datetime("created_at_utc").defaultExpression(CurrentDateTime)
    val modifiedAtUtc = datetime("modified_at_utc").defaultExpression(CurrentDateTime)
}