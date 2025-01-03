package pl.msiwak.interfaces.controller

import pl.msiwak.multiplatform.shared.model.ApiUser

interface UserController {
    suspend fun addUser(name: String, email: String, userId: String)
    suspend fun updateUser(name: String, email: String, userId: String)
    suspend fun getUser(userId: String): ApiUser?
}
