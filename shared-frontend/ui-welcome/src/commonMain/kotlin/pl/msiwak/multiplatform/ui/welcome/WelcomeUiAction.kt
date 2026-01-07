package pl.msiwak.multiplatform.ui.welcome

sealed class WelcomeUiAction {
    data object OnConfirmDialogButtonClicked : WelcomeUiAction()
    data class OnLoginChanged(val login: String) : WelcomeUiAction()
    data class OnPasswordChanged(val password: String) : WelcomeUiAction()
    data object OnVisibilityClicked : WelcomeUiAction()
    data object OnLoginClicked : WelcomeUiAction()
    data object OnOfflineModeClicked : WelcomeUiAction()
    data object OnRegistrationClicked : WelcomeUiAction()
    data object OnGoogleLoginClicked : WelcomeUiAction()
    data class OnGoogleLoginSucceed(val idToken: String, val accessToken: String?) : WelcomeUiAction()
    data class OnAppleLoginSucceed(val tokenString: String, val nonce: String) : WelcomeUiAction()
}
