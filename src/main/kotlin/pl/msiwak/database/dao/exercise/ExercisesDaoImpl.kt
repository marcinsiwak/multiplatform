package pl.msiwak.database.dao.exercise

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import pl.msiwak.auth.PrincipalProvider
import pl.msiwak.database.dao.dbQuery
import pl.msiwak.database.dao.insertWithAudit
import pl.msiwak.database.dao.upsertWithAudit
import pl.msiwak.database.table.Categories
import pl.msiwak.database.table.Exercises
import pl.msiwak.database.table.Results
import pl.msiwak.entities.CategoryEntity
import pl.msiwak.entities.ExerciseEntity
import pl.msiwak.entities.ResultEntity

class ExercisesDaoImpl(private val principalProvider: PrincipalProvider) : ExercisesDao {

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
            resultsBeforeUpdate.forEach {  resultBeforeUpdate ->
                Results.deleteWhere { id eq resultBeforeUpdate.id!! }
            }
        }
    }

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
        val userId = principalProvider.getPrincipal()
        val userId2 = userId.userId
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
            Categories.deleteWhere { id eq categoryId and (userId eq principalProvider.getPrincipal().userId) }
        }
    }

    override suspend fun removeExercise(exerciseId: String) {
        dbQuery {
            Exercises.deleteWhere { id eq exerciseId }
        }
    }

    override suspend fun removeResult(resultId: String) {
        dbQuery {
            Results.deleteWhere { id eq resultId }
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
