package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
class ApiCategorySyncRequest(
    val id: String,
    val name: String,
    val exerciseType: String
)
