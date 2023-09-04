package pl.msiwak.multiplatform.core.api.data.user

import kotlinx.serialization.Serializable

@Serializable
class ApiCategory(
    val categoryId: String = "",
    val name: String,
    val exerciseType: String
)