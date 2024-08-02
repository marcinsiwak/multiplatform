package pl.msiwak.database.dao.user

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import pl.msiwak.database.dao.dbQuery
import pl.msiwak.database.table.Categories
import pl.msiwak.database.table.Exercises
import pl.msiwak.entities.CategoryEntity
import pl.msiwak.entities.ExerciseEntity

class ExercisesDaoImpl : ExercisesDao {

    override suspend fun addExercise(categoryEntity: CategoryEntity) {
        categoryEntity.exercises.map { exercise ->
            addExercise(exercise = exercise, categoryId = categoryEntity.id!!)
        }
    }

    private suspend fun addExercise(exercise: ExerciseEntity, categoryId: String) = dbQuery {
        val insertStatement = Exercises.insert {
            it[this.id] = exercise.id!!
            it[this.categoryId] = categoryId
            it[this.name] = exercise.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToExercise)
    }

    override suspend fun addCategory(
        categoryEntity: CategoryEntity
    ): CategoryEntity? = dbQuery {
        val insertStatement = Categories.insert {
            it[this.id] = categoryEntity.id!!
            it[this.name] = categoryEntity.name
            it[this.userId] = categoryEntity.userId
            it[this.exerciseType] = categoryEntity.exerciseType
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun getCategory(categoryId: String): CategoryEntity? = dbQuery {
        val categoryRow = Categories.select { Categories.id eq categoryId }.singleOrNull()
        categoryRow?.let {
            val exercises = Exercises.select { Exercises.categoryId eq categoryId }.map(::resultRowToExercise)
            resultRowToCategory(it, exercises)
        }
    }

    override suspend fun getCategories(userId: String): List<CategoryEntity> = dbQuery {
        val categories = Categories.select { Categories.userId eq userId }.map(::resultRowToCategory)
        categories.map { category ->
            val exercisesList = Exercises.selectAll().map(::resultRowToExercise)
            val out = exercisesList.toHashSet()
            val exercises =
                Exercises.select { Exercises.categoryId eq category.id!! }.map(::resultRowToExercise).toHashSet()
            category.copy(exercises = exercises)
        }
    }

    override suspend fun removeCategory(categoryId: String) {
        dbQuery {
            Categories.deleteWhere {
                Categories.id eq categoryId
            }
        }
    }

    private fun resultRowToCategory(row: ResultRow, exercises: List<ExerciseEntity> = emptyList()) = CategoryEntity(
        id = row[Categories.id],
        name = row[Categories.name],
        userId = row[Categories.userId],
        exerciseType = row[Categories.exerciseType]
    )

    private fun resultRowToExercise(row: ResultRow) = ExerciseEntity(
        id = row[Exercises.id],
        categoryId = row[Exercises.categoryId],
        name = row[Exercises.name]
    )
}
