package pl.msiwak.multiplatform.ui.verifyEmail

sealed class VerifyEmailUiAction {
    data object OnResendMailClicked : VerifyEmailUiAction()
    data object OnLoginClicked : VerifyEmailUiAction()
}
