package pl.msiwak.multiplatform.ui.register

sealed class RegisterEvent {
    data object NavigateToVerifyEmail : RegisterEvent()
}
