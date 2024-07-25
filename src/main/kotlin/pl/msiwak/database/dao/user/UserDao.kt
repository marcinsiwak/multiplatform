package pl.msiwak.database.dao.user

import pl.msiwak.entities.UserEntity

interface UserDao {
    suspend fun getUser(id: String)
    suspend fun addNewUser(userId: String, name: String, email: String): UserEntity?
}
