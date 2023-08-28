package pl.msiwak.multiplatform.mapper

import pl.msiwak.multiplatform.api.data.user.ApiUser
import pl.msiwak.multiplatform.data.common.User

class UserMapper : Mapper<ApiUser, User>() {
    override fun map(value: ApiUser): User {
        return User(value.email, value.username)
    }
}