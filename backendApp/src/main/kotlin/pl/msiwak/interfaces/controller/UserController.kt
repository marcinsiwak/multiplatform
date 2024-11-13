package pl.msiwak.interfaces.controller

import pl.msiwak.interfaces.dtos.UserDTO

interface UserController {
    suspend fun addUser(name: String, email: String, userId: String)
    suspend fun getUser(userId: String): UserDTO?
}
