package pl.msiwak.domain.repositories

import pl.msiwak.domain.entities.UserEntity
import pl.msiwak.infrastructure.database.dao.user.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(id: String, name: String, email: String) {
        userDao.addNewUser(id, name, email)
    }

    suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }
}
