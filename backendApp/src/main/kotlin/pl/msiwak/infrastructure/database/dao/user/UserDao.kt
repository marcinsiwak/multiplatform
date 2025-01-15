package pl.msiwak.infrastructure.database.dao.user

import pl.msiwak.infrastructure.entities.UserEntity

interface UserDao {
    suspend fun getUser(id: String): UserEntity?
    suspend fun addNewUser(userId: String, name: String, email: String, role: String): UserEntity?
    suspend fun updateUser(userId: String, name: String? = null, email: String? = null, role: String? = null)
}
