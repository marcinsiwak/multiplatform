package pl.msiwak.interfaces.mapper

import pl.msiwak.infrastructure.entities.UserEntity
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.model.ApiUser

class ApiUserMapper : Mapper<UserEntity, ApiUser>() {
    override fun map(value: UserEntity): ApiUser = with(value) {
        return ApiUser(
            id = id,
            email = email,
            username = name,
            role = mapRole(value.role)
        )
    }

    private fun mapRole(roleString: String): Role {
        return when (roleString) {
            "admin" -> Role.ADMIN
            else -> Role.USER
        }
    }
}
