package pl.msiwak.multiplatform.core.mapper

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.core.api.data.user.ApiUser

class UserMapper : Mapper<ApiUser, User>() {
    override fun map(value: ApiUser): User {
        return User(value.email, value.username)
    }
}