package pl.msiwak.multiplatform.data.common

data class PasswordRequirement(
    val isCorrect: Boolean,
    val type: PasswordRequirementType
)