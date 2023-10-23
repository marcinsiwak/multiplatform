package pl.msiwak.multiplatform.database.cache

import app.cash.sqldelight.ExecutableQuery
import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.ExerciseType

public class AppDatabaseQueries(
  driver: SqlDriver,
  private val CategoryDBAdapter: CategoryDB.Adapter,
  private val ExerciseDBAdapter: ExerciseDB.Adapter,
  private val ResultDBAdapter: ResultDB.Adapter,
) : TransacterImpl(driver) {
  public fun <T : Any> selectFromCategory(id: String, mapper: (
    id: String,
    name: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) -> T): Query<T> = SelectFromCategoryQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      CategoryDBAdapter.exerciseTypeAdapter.decode(cursor.getString(2)!!),
      CategoryDBAdapter.creationDateAdapter.decode(cursor.getString(3)!!)
    )
  }

  public fun selectFromCategory(id: String): Query<CategoryDB> = selectFromCategory(id) { id_, name,
      exerciseType, creationDate ->
    CategoryDB(
      id_,
      name,
      exerciseType,
      creationDate
    )
  }

  public fun <T : Any> selectAllFromCategory(mapper: (
    id: String,
    name: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) -> T): Query<T> = Query(166_141_614, arrayOf("CategoryDB"), driver, "AppDatabase.sq",
      "selectAllFromCategory", "SELECT * FROM CategoryDB") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      CategoryDBAdapter.exerciseTypeAdapter.decode(cursor.getString(2)!!),
      CategoryDBAdapter.creationDateAdapter.decode(cursor.getString(3)!!)
    )
  }

  public fun selectAllFromCategory(): Query<CategoryDB> = selectAllFromCategory { id, name,
      exerciseType, creationDate ->
    CategoryDB(
      id,
      name,
      exerciseType,
      creationDate
    )
  }

  public fun <T : Any> selectAllFromExercises(mapper: (
    id: String,
    categoryId: String,
    exerciseTitle: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) -> T): Query<T> = Query(-1_388_900_885, arrayOf("ExerciseDB"), driver, "AppDatabase.sq",
      "selectAllFromExercises", "SELECT * FROM ExerciseDB") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      ExerciseDBAdapter.exerciseTypeAdapter.decode(cursor.getString(3)!!),
      ExerciseDBAdapter.creationDateAdapter.decode(cursor.getString(4)!!)
    )
  }

  public fun selectAllFromExercises(): Query<ExerciseDB> = selectAllFromExercises { id, categoryId,
      exerciseTitle, exerciseType, creationDate ->
    ExerciseDB(
      id,
      categoryId,
      exerciseTitle,
      exerciseType,
      creationDate
    )
  }

  public fun <T : Any> selectFromExercise(id: String, mapper: (
    id: String,
    categoryId: String,
    exerciseTitle: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) -> T): Query<T> = SelectFromExerciseQuery(id) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      ExerciseDBAdapter.exerciseTypeAdapter.decode(cursor.getString(3)!!),
      ExerciseDBAdapter.creationDateAdapter.decode(cursor.getString(4)!!)
    )
  }

  public fun selectFromExercise(id: String): Query<ExerciseDB> = selectFromExercise(id) { id_,
      categoryId, exerciseTitle, exerciseType, creationDate ->
    ExerciseDB(
      id_,
      categoryId,
      exerciseTitle,
      exerciseType,
      creationDate
    )
  }

  public fun <T : Any> selectFromExerciseByCategory(categoryId: String, mapper: (
    id: String,
    categoryId: String,
    exerciseTitle: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) -> T): Query<T> = SelectFromExerciseByCategoryQuery(categoryId) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      ExerciseDBAdapter.exerciseTypeAdapter.decode(cursor.getString(3)!!),
      ExerciseDBAdapter.creationDateAdapter.decode(cursor.getString(4)!!)
    )
  }

  public fun selectFromExerciseByCategory(categoryId: String): Query<ExerciseDB> =
      selectFromExerciseByCategory(categoryId) { id, categoryId_, exerciseTitle, exerciseType,
      creationDate ->
    ExerciseDB(
      id,
      categoryId_,
      exerciseTitle,
      exerciseType,
      creationDate
    )
  }

  public fun <T : Any> selectFromResultsByExercise(exerciseID: String, mapper: (
    id: String,
    exerciseID: String,
    result: String,
    amount: String,
    date: LocalDateTime,
  ) -> T): Query<T> = SelectFromResultsByExerciseQuery(exerciseID) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      ResultDBAdapter.dateAdapter.decode(cursor.getString(4)!!)
    )
  }

  public fun selectFromResultsByExercise(exerciseID: String): Query<ResultDB> =
      selectFromResultsByExercise(exerciseID) { id, exerciseID_, result, amount, date ->
    ResultDB(
      id,
      exerciseID_,
      result,
      amount,
      date
    )
  }

  public fun lastInsertRowId(): ExecutableQuery<Long> = Query(1_734_766_055, driver,
      "AppDatabase.sq", "lastInsertRowId", "SELECT last_insert_rowid()") { cursor ->
    cursor.getLong(0)!!
  }

  public fun updateCategory(
    id: String,
    name: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) {
    driver.execute(771_258_310, """
        |INSERT OR REPLACE INTO CategoryDB(id, name, exerciseType, creationDate)
        |VALUES (?, ?, ?, ?)
        """.trimMargin(), 4) {
          bindString(0, id)
          bindString(1, name)
          bindString(2, CategoryDBAdapter.exerciseTypeAdapter.encode(exerciseType))
          bindString(3, CategoryDBAdapter.creationDateAdapter.encode(creationDate))
        }
    notifyQueries(771_258_310) { emit ->
      emit("CategoryDB")
    }
  }

  public fun insertCategory(
    id: String,
    name: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) {
    driver.execute(1_224_217_014, """
        |INSERT INTO CategoryDB(id, name, exerciseType, creationDate)
        |VALUES (?, ?, ?, ?)
        """.trimMargin(), 4) {
          bindString(0, id)
          bindString(1, name)
          bindString(2, CategoryDBAdapter.exerciseTypeAdapter.encode(exerciseType))
          bindString(3, CategoryDBAdapter.creationDateAdapter.encode(creationDate))
        }
    notifyQueries(1_224_217_014) { emit ->
      emit("CategoryDB")
    }
  }

  public fun removeAllCategories() {
    driver.execute(1_483_305_210, """DELETE FROM CategoryDB""", 0)
    notifyQueries(1_483_305_210) { emit ->
      emit("CategoryDB")
    }
  }

  public fun removeCategory(id: String) {
    driver.execute(-1_693_365_119, """
        |DELETE FROM CategoryDB
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(-1_693_365_119) { emit ->
      emit("CategoryDB")
    }
  }

  public fun deleteCategories() {
    driver.execute(-2_087_286_458, """DELETE FROM CategoryDB""", 0)
    notifyQueries(-2_087_286_458) { emit ->
      emit("CategoryDB")
    }
  }

  public fun updateExercise(
    id: String,
    categoryId: String,
    exerciseTitle: String,
    exerciseType: ExerciseType,
    creationDate: LocalDateTime,
  ) {
    driver.execute(-1_517_896_544, """
        |INSERT OR REPLACE INTO ExerciseDB(id, categoryId, exerciseTitle, exerciseType, creationDate)
        |VALUES (?, ?,?, ?, ?)
        """.trimMargin(), 5) {
          bindString(0, id)
          bindString(1, categoryId)
          bindString(2, exerciseTitle)
          bindString(3, ExerciseDBAdapter.exerciseTypeAdapter.encode(exerciseType))
          bindString(4, ExerciseDBAdapter.creationDateAdapter.encode(creationDate))
        }
    notifyQueries(-1_517_896_544) { emit ->
      emit("ExerciseDB")
    }
  }

  public fun removeAllExercises() {
    driver.execute(-533_906_691, """DELETE FROM ExerciseDB""", 0)
    notifyQueries(-533_906_691) { emit ->
      emit("ExerciseDB")
    }
  }

  public fun removeExercise(id: String) {
    driver.execute(312_447_323, """
        |DELETE FROM ExerciseDB
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(312_447_323) { emit ->
      emit("ExerciseDB")
    }
  }

  public fun updateResult(
    id: String,
    exerciseID: String,
    result: String,
    amount: String,
    date: LocalDateTime,
  ) {
    driver.execute(1_770_231_013, """
        |INSERT OR REPLACE INTO ResultDB(id, exerciseID, result, amount, date)
        |VALUES (?, ?,?, ?, ?)
        """.trimMargin(), 5) {
          bindString(0, id)
          bindString(1, exerciseID)
          bindString(2, result)
          bindString(3, amount)
          bindString(4, ResultDBAdapter.dateAdapter.encode(date))
        }
    notifyQueries(1_770_231_013) { emit ->
      emit("ResultDB")
    }
  }

  public fun removeResult(id: String) {
    driver.execute(1_857_051_744, """
        |DELETE FROM ResultDB
        |WHERE ResultDB.id = ?
        """.trimMargin(), 1) {
          bindString(0, id)
        }
    notifyQueries(1_857_051_744) { emit ->
      emit("ResultDB")
    }
  }

  private inner class SelectFromCategoryQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("CategoryDB", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("CategoryDB", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-796_766_909, """
    |SELECT * FROM CategoryDB
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "AppDatabase.sq:selectFromCategory"
  }

  private inner class SelectFromExerciseQuery<out T : Any>(
    public val id: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ExerciseDB", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ExerciseDB", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_209_045_533, """
    |SELECT * FROM ExerciseDB
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, id)
    }

    override fun toString(): String = "AppDatabase.sq:selectFromExercise"
  }

  private inner class SelectFromExerciseByCategoryQuery<out T : Any>(
    public val categoryId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ExerciseDB", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ExerciseDB", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-703_654_798, """
    |SELECT * FROM ExerciseDB
    |WHERE categoryId = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, categoryId)
    }

    override fun toString(): String = "AppDatabase.sq:selectFromExerciseByCategory"
  }

  private inner class SelectFromResultsByExerciseQuery<out T : Any>(
    public val exerciseID: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ResultDB", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ResultDB", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(656_553_280, """
    |SELECT * FROM ResultDB
    |WHERE exerciseID = ?
    """.trimMargin(), mapper, 1) {
      bindString(0, exerciseID)
    }

    override fun toString(): String = "AppDatabase.sq:selectFromResultsByExercise"
  }
}
