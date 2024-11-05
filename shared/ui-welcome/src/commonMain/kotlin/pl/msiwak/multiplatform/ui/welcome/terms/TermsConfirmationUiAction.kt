package pl.msiwak.multiplatform.ui.welcome.terms

sealed class TermsConfirmationUiAction {
    data object OnConfirmSynchronizationClicked : TermsConfirmationUiAction()
    data object OnDismissSynchronizationClicked : TermsConfirmationUiAction()
    data class OnButtonClick(val idToken: String?, val accessToken: String?) :
        TermsConfirmationUiAction()

    data object OnTermsClick : TermsConfirmationUiAction()
}
