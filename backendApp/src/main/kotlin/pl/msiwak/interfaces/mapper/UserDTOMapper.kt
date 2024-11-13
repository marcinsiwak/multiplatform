package pl.msiwak.interfaces.mapper

import pl.msiwak.domain.entities.UserEntity
import pl.msiwak.interfaces.dtos.UserDTO

class UserDTOMapper : Mapper<UserEntity, UserDTO>() {
    override fun map(value: UserEntity): UserDTO = with(value) {
        return UserDTO(
            id = id,
            email = email,
            username = name
        )
    }
}
