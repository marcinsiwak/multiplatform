package pl.msiwak.multiplatform.ui.verifyEmail

sealed class VerifyEmailEvent {
    object OpenMail: VerifyEmailEvent()
}