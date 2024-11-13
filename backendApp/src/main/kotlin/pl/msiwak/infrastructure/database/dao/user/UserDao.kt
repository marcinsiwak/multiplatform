package pl.msiwak.infrastructure.database.dao.user

import pl.msiwak.domain.entities.UserEntity

interface UserDao {
    suspend fun getUser(id: String): UserEntity?
    suspend fun addNewUser(userId: String, name: String, email: String): UserEntity?
}
