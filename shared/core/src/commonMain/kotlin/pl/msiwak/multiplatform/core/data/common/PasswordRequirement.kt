package pl.msiwak.multiplatform.core.data.common

data class PasswordRequirement(
    val isCorrect: Boolean,
    val type: PasswordRequirementType
)