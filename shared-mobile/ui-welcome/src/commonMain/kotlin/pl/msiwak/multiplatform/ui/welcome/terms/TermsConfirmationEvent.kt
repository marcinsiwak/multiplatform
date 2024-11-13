package pl.msiwak.multiplatform.ui.welcome.terms

sealed class TermsConfirmationEvent {
    data object NavigateToDashboard : TermsConfirmationEvent()
}
