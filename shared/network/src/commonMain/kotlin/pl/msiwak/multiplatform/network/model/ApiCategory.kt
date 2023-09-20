package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiCategory(
    val categoryId: String = "",
    val name: String,
    val exerciseType: String
)