package pl.msiwak.database.dao.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import pl.msiwak.database.dao.dbQuery
import pl.msiwak.database.table.Users
import pl.msiwak.entities.UserEntity

class UserDaoImpl : UserDao {
    override suspend fun getUser(id: String) {
        Users.select {
            Users.id eq id
        }
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(userId: String, name: String, email: String) = dbQuery {
        val insertStatement = Users.insert {
            it[id] = userId
            it[Users.name] = name
            it[Users.email] = email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    private fun resultRowToUser(row: ResultRow) = UserEntity(
        id = row[Users.id],
        name = row[Users.name],
        email = row[Users.email]
    )
}
