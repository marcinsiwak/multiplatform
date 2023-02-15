package pl.msiwak.multiplatform.data.entity

data class Summary(
    val id: Long = 0,
    val exerciseType: String,
    val results: List<String>
)