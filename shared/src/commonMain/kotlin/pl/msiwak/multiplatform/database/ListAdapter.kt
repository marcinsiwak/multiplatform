package pl.msiwak.multiplatform.database

import com.squareup.sqldelight.ColumnAdapter

val listAdapter = object : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String): List<String> {
        return if (databaseValue.isEmpty()) {
            listOf()
        } else {
            databaseValue.split(",")
        }
    }

    override fun encode(value: List<String>): String {
        return value.joinToString(separator = ",")
    }
}
