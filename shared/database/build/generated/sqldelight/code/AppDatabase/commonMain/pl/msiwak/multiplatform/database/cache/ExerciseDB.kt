package pl.msiwak.multiplatform.database.cache

import app.cash.sqldelight.ColumnAdapter
import kotlin.String
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.ExerciseType

public data class ExerciseDB(
  public val id: String,
  public val categoryId: String,
  public val exerciseTitle: String,
  public val exerciseType: ExerciseType,
  public val creationDate: LocalDateTime,
) {
  public class Adapter(
    public val exerciseTypeAdapter: ColumnAdapter<ExerciseType, String>,
    public val creationDateAdapter: ColumnAdapter<LocalDateTime, String>,
  )
}
