package pl.msiwak.database.dao.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import pl.msiwak.database.dao.dbQuery
import pl.msiwak.database.table.Categories
import pl.msiwak.entities.CategoryEntity

class ExercisesDaoImpl : ExercisesDao {

    override suspend fun addCategory(
        categoryUuid: String,
        userId: String,
        name: String,
        exerciseType: String
    ): CategoryEntity? = dbQuery {
        val insertStatement = Categories.insert {
            it[this.id] = categoryUuid
            it[this.name] = name
            it[this.userId] = userId
            it[this.exerciseType] = exerciseType
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCategory)
    }

    override suspend fun getCategory(categoryId: String): CategoryEntity? = dbQuery {
        return@dbQuery Categories.select {
            Categories.id eq categoryId
        }
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun getCategories(userId: String): List<CategoryEntity> = dbQuery {
        return@dbQuery Categories.select {
            Categories.userId eq userId
        }
            .map(::resultRowToCategory)
    }

    override suspend fun removeCategory(categoryId: String) {
        dbQuery {
            Categories.deleteWhere {
                Categories.id eq categoryId
            }
        }
    }


    private fun resultRowToCategory(row: ResultRow) = CategoryEntity(
        id = row[Categories.id],
        name = row[Categories.name],
        userId = row[Categories.userId],
        exerciseType = row[Categories.exerciseType]
    )
}
