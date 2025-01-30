package pl.msiwak.multiplatform.ui.verifyEmail

data class VerifyState(
    val isLoading: Boolean = false,
    val resendDelay: Int = 0
)
