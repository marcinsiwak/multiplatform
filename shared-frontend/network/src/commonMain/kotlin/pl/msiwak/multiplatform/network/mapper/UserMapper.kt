package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.model.ApiUser

class UserMapper : Mapper<ApiUser, User>() {
    override fun map(value: ApiUser): User {
        return User(
            value.id,
            value.email,
            value.username ?: "",
            value.role ?: Role.USER
        )
    }
}
