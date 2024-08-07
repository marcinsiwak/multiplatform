package pl.msiwak.database.dao.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import pl.msiwak.auth.PrincipalProvider
import pl.msiwak.database.dao.dbQuery
import pl.msiwak.database.dao.insertWithAudit
import pl.msiwak.database.table.Categories
import pl.msiwak.database.table.Exercises
import pl.msiwak.database.table.Results
import pl.msiwak.entities.CategoryEntity
import pl.msiwak.entities.ExerciseEntity
import pl.msiwak.entities.ResultEntity

class ExercisesDaoImpl(private val principalProvider: PrincipalProvider) : ExercisesDao {

    override suspend fun addExercise(categoryEntity: CategoryEntity) {
        categoryEntity.exercises.map { exercise ->
            addExercise(exercise = exercise, categoryId = categoryEntity.id!!)
        }
    }

    private suspend fun addExercise(exercise: ExerciseEntity, categoryId: String) = dbQuery {
        val insertStatement = insertWithAudit(Exercises) {
            it[this.id] = exercise.id!!
            it[this.categoryId] = categoryId
            it[this.name] = exercise.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToExercise)
    }

    override suspend fun addCategory(categoryEntity: CategoryEntity): CategoryEntity? = dbQuery {
        val insertStatement = insertWithAudit(Categories) {
            it[id] = categoryEntity.id!!
            it[name] = categoryEntity.name
            it[userId] = categoryEntity.userId
            it[exerciseType] = categoryEntity.exerciseType
        }
        insertStatement.resultedValues?.single()?.let(::resultRowToCategory)
    }

    override suspend fun addResult(categoryEntity: CategoryEntity) {
        val exercise = categoryEntity.exercises.find { it.categoryId == categoryEntity.id } ?: return
        exercise.results.map { addResult(exercise.id!!, it) }
    }

    private suspend fun addResult(exerciseId: String, resultEntity: ResultEntity) = dbQuery {
        val insertStatement = insertWithAudit(Results) {
            it[this.id] = resultEntity.id!!
            it[this.exerciseId] = exerciseId
            it[this.amount] = resultEntity.amount
            it[this.result] = resultEntity.result
            it[this.date] = resultEntity.date
        }
        insertStatement.resultedValues?.single()?.let(::resultRowToResult)
    }

    override suspend fun getCategory(categoryId: String): CategoryEntity? = dbQuery {
        getCategoryEntity(categoryId, principalProvider.getPrincipal().userId)
    }

    private fun getCategoryEntity(categoryId: String, userId: String): CategoryEntity? {
        val categoryRow =
            Categories.selectAll().where { Categories.id eq categoryId }.andWhere { Categories.userId eq userId }
                .singleOrNull()
        categoryRow?.let {
            val exercises =
                Exercises.selectAll().where { Exercises.categoryId eq categoryId }.map(::resultRowToExercise)
            return resultRowToCategory(it, exercises)
        }
        return null
    }

    override suspend fun getCategories(): List<CategoryEntity> {
        val userId = principalProvider.getPrincipal().userId
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
        val exercise = getExercise(exerciseId)
        val categoryId = exercise.categoryId ?: return@dbQuery null
        val category = getCategoryEntity(categoryId, principalProvider.getPrincipal().userId)
        return@dbQuery category
    }

    override suspend fun removeCategory(categoryId: String) {
        dbQuery {
            Categories.deleteWhere {
                id eq categoryId
            }
        }
    }

    private fun getExercise(exerciseId: String): ExerciseEntity {
        val results = getResults(exerciseId)
        val exercise =
            Exercises.selectAll().where { Exercises.id eq exerciseId }.single().let { resultRowToExercise(it, results) }
        return exercise
    }

    private fun getResults(exerciseId: String): List<ResultEntity> {
        val results = Results.selectAll().where { Results.id eq exerciseId }.map(::resultRowToResult)
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
        amount = row[Results.amount],
        result = row[Results.result],
        date = row[Results.date]
    )
}
