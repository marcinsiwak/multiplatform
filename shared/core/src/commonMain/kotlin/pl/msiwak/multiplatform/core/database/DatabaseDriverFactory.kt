package pl.msiwak.multiplatform.core.database

expect class DatabaseDriverFactory {
    fun createDriver() : app.cash.sqldelight.db.SqlDriver
}