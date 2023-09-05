package pl.msiwak.multiplatform.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.commonObject.ResultData

val resultListAdapter = object : ColumnAdapter<List<ResultData>, String> {
    override fun decode(databaseValue: String): List<ResultData> {
        return if (databaseValue.isEmpty()) {
            listOf()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }


    override fun encode(value: List<ResultData>): String {
        return Json.encodeToString(value)
    }
}