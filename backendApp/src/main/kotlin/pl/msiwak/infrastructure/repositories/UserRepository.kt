package pl.msiwak.infrastructure.repositories

import pl.msiwak.infrastructure.config.auth.role.RoleManager
import pl.msiwak.infrastructure.database.dao.user.UserDao
import pl.msiwak.infrastructure.entities.UserEntity
import pl.msiwak.multiplatform.shared.common.Role

class UserRepository(
    private val userDao: UserDao,
    private val roleManager: RoleManager
) {
    suspend fun addUser(id: String, name: String, email: String) {
        val role = if (email == "marcinsiwak15@gmail.com") {
            Role.ADMIN
        } else {
            Role.USER
        }
        roleManager.setRole(id, role)
        userDao.addNewUser(id, name, email, role.name)
    }

    suspend fun updateUser(userEntity: UserEntity) {
        val role = if (userEntity.email == "marcinsiwak15@gmail.com") {
            Role.ADMIN
        } else {
            Role.USER
        }
        roleManager.setRole(userEntity.id, role)
        userDao.updateUser(userEntity)
    }

    suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }

    suspend fun getUserByToken(deviceToken: String): UserEntity? {
        return userDao.getUserByDeviceToken(deviceToken)
    }

    suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }
}
