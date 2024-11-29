package pl.msiwak.multiplatform.network.mapper

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.shared.model.ApiUser

class UserMapper : Mapper<ApiUser, User>() {
    override fun map(value: ApiUser): User {
        return User(value.email ?: "", value.username ?: "")
    }
}
