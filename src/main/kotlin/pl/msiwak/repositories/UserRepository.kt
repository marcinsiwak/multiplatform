package pl.msiwak.repositories

import pl.msiwak.database.dao.user.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(id: String, name: String, email: String) {
        userDao.addNewUser(id, name, email)
    }
}
