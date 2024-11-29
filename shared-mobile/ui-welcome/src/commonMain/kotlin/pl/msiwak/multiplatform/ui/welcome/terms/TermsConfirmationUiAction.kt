package pl.msiwak.multiplatform.ui.welcome.terms

sealed class TermsConfirmationUiAction {
    data object OnConfirmSynchronizationClicked : TermsConfirmationUiAction()
    data object OnDismissSynchronizationClicked : TermsConfirmationUiAction()
    data class OnButtonClick(val uuid: String) : TermsConfirmationUiAction()
    data object OnTermsClick : TermsConfirmationUiAction()
}
