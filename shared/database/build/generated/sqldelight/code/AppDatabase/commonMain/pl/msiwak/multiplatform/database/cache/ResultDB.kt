package pl.msiwak.multiplatform.database.cache

import app.cash.sqldelight.ColumnAdapter
import kotlin.String
import kotlinx.datetime.LocalDateTime

public data class ResultDB(
  public val id: String,
  public val exerciseID: String,
  public val result: String,
  public val amount: String,
  public val date: LocalDateTime,
) {
  public class Adapter(
    public val dateAdapter: ColumnAdapter<LocalDateTime, String>,
  )
}
