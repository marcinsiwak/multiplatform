package pl.msiwak.queries

import pl.msiwak.dtos.UserDTO

interface GetUserQuery {
    suspend fun invoke(id: String): UserDTO?
}