package pl.msiwak.interfaces.mapper

import pl.msiwak.infrastructure.entities.UserEntity
import pl.msiwak.multiplatform.shared.model.ApiUser

class ApiUserMapper : Mapper<UserEntity, ApiUser>() {
    override fun map(value: UserEntity): ApiUser = with(value) {
        return ApiUser(
            id = id,
            email = email,
            username = name
        )
    }
}
