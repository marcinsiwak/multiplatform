package pl.msiwak.multiplatform.commonObject

data class PasswordRequirement(
    val isCorrect: Boolean,
    val type: PasswordRequirementType
)
