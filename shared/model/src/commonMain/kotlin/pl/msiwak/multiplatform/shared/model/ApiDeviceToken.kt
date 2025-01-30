package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable
import pl.msiwak.multiplatform.shared.common.Role

@Serializable
data class ApiDeviceToken(
    val token: String
)
