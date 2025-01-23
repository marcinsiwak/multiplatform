package pl.msiwak.interfaces.controller

import pl.msiwak.multiplatform.shared.model.ApiUser

interface UserController {
    suspend fun addUser(userId: String, name: String, email: String)
    suspend fun updateUser(userId: String, name: String, email: String)
    suspend fun getUser(userId: String): ApiUser?
    suspend fun getUsers(): List<ApiUser>
}
