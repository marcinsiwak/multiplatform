package pl.msiwak.infrastructure.database.dao.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import pl.msiwak.infrastructure.database.dao.dbQuery
import pl.msiwak.infrastructure.database.table.Users
import pl.msiwak.infrastructure.entities.UserEntity

class UserDaoImpl : UserDao {
    override suspend fun getUser(id: String): UserEntity? = dbQuery {
        return@dbQuery Users.selectAll().where { Users.id eq id }
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(userId: String, name: String, email: String, role: String) = dbQuery {
        val insertStatement = Users.insert {
            it[id] = userId
            it[Users.name] = name
            it[Users.email] = email
            it[Users.role] = role
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    private fun resultRowToUser(row: ResultRow) = UserEntity(
        id = row[Users.id],
        name = row[Users.name],
        email = row[Users.email],
        role = row[Users.role]
    )
}
