package pl.msiwak.queries

import pl.msiwak.dtos.UserDTO

interface GetUserQuery {
    suspend fun invoke(userId: String): UserDTO?
}