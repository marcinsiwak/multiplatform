package pl.msiwak.multiplatform.ui.welcome.terms

sealed class TermsConfirmationUiAction {
    data object OnButtonClick : TermsConfirmationUiAction()
    data object OnTermsClick : TermsConfirmationUiAction()
}
