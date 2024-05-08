package pl.msiwak.multiplatform.ui.welcome

sealed class WelcomeEvent {
    data object NavigateToDashboard: WelcomeEvent()
    data object NavigateToVerifyEmail: WelcomeEvent()
    data object NavigateToRegistration: WelcomeEvent()
}