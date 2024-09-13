package pl.msiwak.infrastructure.database.dao.exercise

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.batchUpsert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.domain.entities.ExerciseEntity
import pl.msiwak.domain.entities.ResultEntity
import pl.msiwak.infrastructure.database.dao.dbQuery
import pl.msiwak.infrastructure.database.dao.upsertWithAudit
import pl.msiwak.infrastructure.database.table.Categories
import pl.msiwak.infrastructure.database.table.Exercises
import pl.msiwak.infrastructure.database.table.Results

class ExercisesDaoImpl : ExercisesDao {

    override suspend fun updateCategory(category: CategoryEntity) {
        dbQuery {
            upsertWithAudit(Categories) {
                it[id] = category.id!!
                it[name] = category.name
                it[userId] = category.userId
                it[exerciseType] = category.exerciseType
            }

            val exercisesBeforeUpdate = Exercises
                .selectAll()
                .where { Exercises.categoryId eq category.id!! }
                .map(::resultRowToExercise)
                .toMutableList()


            category.exercises.forEach { updatedExercise ->
                upsertWithAudit(Exercises) {
                    it[this.id] = updatedExercise.id!!
                    it[this.categoryId] = updatedExercise.categoryId!!
                    it[this.name] = updatedExercise.name
                }
                exercisesBeforeUpdate.removeIf { it.id == updatedExercise.id }
            }
            exercisesBeforeUpdate.forEach { exerciseBeforeUpdate ->
                Exercises.deleteWhere { id eq exerciseBeforeUpdate.id!! }
            }
        }
    }

    override suspend fun updateExercises(category: CategoryEntity) {
        dbQuery {
            val exercisesBeforeUpdate = Exercises
                .selectAll()
                .where { Exercises.categoryId eq category.id!! }
                .map(::resultRowToExercise)
                .toMutableList()

            category.exercises.forEach { updatedExercise ->
                upsertWithAudit(Exercises) {
                    it[this.id] = updatedExercise.id!!
                    it[this.categoryId] = updatedExercise.categoryId!!
                    it[this.name] = updatedExercise.name
                }
                exercisesBeforeUpdate.removeIf { it.id == updatedExercise.id }
            }
            exercisesBeforeUpdate.forEach { exerciseBeforeUpdate ->
                Exercises.deleteWhere { id eq exerciseBeforeUpdate.id!! }
            }
        }
    }

    override suspend fun updateResults(category: CategoryEntity) {
        dbQuery {
            val resultsBeforeUpdate = Results
                .selectAll()
                .where { Results.exerciseId inList category.exercises.map { it.id!! } }
                .map(::resultRowToResult)
                .toMutableList()

            category.exercises.flatMap { it.results }.forEach { updatedResult ->
                upsertWithAudit(Results) {
                    it[this.id] = updatedResult.id!!
                    it[this.exerciseId] = updatedResult.exerciseId!!
                    it[this.amount] = updatedResult.amount
                    it[this.result] = updatedResult.result
                    it[this.date] = updatedResult.date
                }
                resultsBeforeUpdate.removeIf { it.id == updatedResult.id }
            }
            resultsBeforeUpdate.forEach { resultBeforeUpdate ->
                Results.deleteWhere { id eq resultBeforeUpdate.id!! }
            }
        }
    }

    override suspend fun getCategory(categoryId: String): CategoryEntity? = dbQuery {
        getCategoryEntity(categoryId)
    }

    private fun getCategoryEntity(categoryId: String): CategoryEntity? {
        val categoryRow =
            Categories.selectAll().where { Categories.id eq categoryId }
                .singleOrNull()
        categoryRow?.let {
            val exercises =
                Exercises.selectAll().where { Exercises.categoryId eq categoryId }.map(::resultRowToExercise)
            return resultRowToCategory(it, exercises)
        }
        return null
    }

    override suspend fun getCategories(userId: String): List<CategoryEntity> {
        return dbQuery {
            val categories = Categories.selectAll().where { Categories.userId eq userId }.map(::resultRowToCategory)
            return@dbQuery categories.map { category ->
                val exercises =
                    Exercises.selectAll().where { Exercises.categoryId eq category.id!! }
                        .map { resultRowToExercise(it) }
                        .map {
                            val results =
                                Results.selectAll().map(::resultRowToResult)
                            it.copy(results = results.toHashSet())
                        }.toHashSet()
                category.copy(exercises = exercises)
            }
        }
    }

    override suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity? = dbQuery {
        return@dbQuery getExercise(exerciseId).categoryId?.let {
            getCategoryEntity(it)
        }
    }

    override suspend fun getCategoryByResult(resultId: String): CategoryEntity? = dbQuery {
        val result = Results.selectAll().where { Results.id eq resultId }.map(::resultRowToResult).singleOrNull()
            ?: return@dbQuery null
        val exercise = Exercises.selectAll().where { Exercises.id eq result.exerciseId!! }.map(::resultRowToExercise)
            .singleOrNull() ?: return@dbQuery null
        val exercises = Exercises.selectAll().where { Exercises.categoryId eq exercise.categoryId!! }.map {
            val exerciseId = it[Exercises.id]
            val results = getResultsByExercise(exerciseId)
            resultRowToExercise(it, results)
        }

        return@dbQuery Categories.selectAll().where { Categories.id eq exercise.categoryId!! }
            .map { resultRowToCategory(it, exercises) }.single()
    }

    override suspend fun removeCategory(categoryId: String) {
        dbQuery {
            Categories.deleteWhere { id eq categoryId }
        }
    }

    override suspend fun syncCategories(categories: List<CategoryEntity>) {
        dbQuery {
            val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            Categories.batchUpsert(
                data = categories,
                onUpdateExclude = listOf(Categories.createdAtUtc)
            ) { category ->
                this[Categories.id] = category.id!!
                this[Categories.name] = category.name
                this[Categories.userId] = category.userId
                this[Categories.exerciseType] = category.exerciseType
                this[Categories.createdAtUtc] = now
                this[Categories.modifiedAtUtc] = now
            }
        }
    }

    override suspend fun syncExercises(exercises: List<ExerciseEntity>) {
        dbQuery {
            val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            Exercises.batchUpsert(
                data = exercises,
                onUpdateExclude = listOf(Exercises.createdAtUtc)
            ) { exercise ->
                this[Exercises.id] = exercise.id!!
                this[Exercises.categoryId] = exercise.categoryId!!
                this[Exercises.name] = exercise.name
                this[Exercises.createdAtUtc] = now
                this[Exercises.modifiedAtUtc] = now
            }
        }
    }

    override suspend fun syncResults(results: List<ResultEntity>) {
        dbQuery {
            val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            Results.batchUpsert(
                data = results,
                onUpdateExclude = listOf(Categories.createdAtUtc)
            ) { result ->
                this[Results.id] = result.id!!
                this[Results.exerciseId] = result.exerciseId!!
                this[Results.amount] = result.amount
                this[Results.result] = result.result
                this[Results.date] = result.date
                this[Results.createdAtUtc] = now
                this[Results.modifiedAtUtc] = now
            }
        }
    }

    private fun getExercise(exerciseId: String): ExerciseEntity {
        val results = getResultsByExercise(exerciseId)
        val exercise =
            Exercises.selectAll().where { Exercises.id eq exerciseId }.single().let { resultRowToExercise(it, results) }
        return exercise
    }

    private fun getResultsByExercise(exerciseId: String): List<ResultEntity> {
        val results = Results.selectAll().where { Results.exerciseId eq exerciseId }.map(::resultRowToResult)
        return results
    }

    private fun resultRowToCategory(row: ResultRow, exercises: List<ExerciseEntity> = emptyList()) = CategoryEntity(
        id = row[Categories.id],
        name = row[Categories.name],
        userId = row[Categories.userId],
        exerciseType = row[Categories.exerciseType],
        exercises = exercises.toHashSet()
    )

    private fun resultRowToExercise(row: ResultRow, results: List<ResultEntity> = emptyList()) = ExerciseEntity(
        id = row[Exercises.id],
        categoryId = row[Exercises.categoryId],
        name = row[Exercises.name],
        results = results.toHashSet()
    )

    private fun resultRowToResult(row: ResultRow) = ResultEntity(
        id = row[Results.id],
        exerciseId = row[Results.exerciseId],
        amount = row[Results.amount],
        result = row[Results.result],
        date = row[Results.date]
    )
}
