package pl.msiwak.infrastructure.repositories

import pl.msiwak.infrastructure.entities.UserEntity
import pl.msiwak.infrastructure.config.auth.roles.Role
import pl.msiwak.infrastructure.config.auth.roles.RoleManager
import pl.msiwak.infrastructure.database.dao.user.UserDao

class UserRepository(
    private val userDao: UserDao,
    private val roleManager: RoleManager
) {

    suspend fun addUser(id: String, name: String, email: String) {
        userDao.addNewUser(id, name, email)
        roleManager.setRole(id, Role.USER)
    }

    suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }
}
