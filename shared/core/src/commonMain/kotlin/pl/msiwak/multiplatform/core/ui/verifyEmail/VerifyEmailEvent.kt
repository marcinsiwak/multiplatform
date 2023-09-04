package pl.msiwak.multiplatform.core.ui.verifyEmail

sealed class VerifyEmailEvent {
    object OpenMail: VerifyEmailEvent()
}