package pl.msiwak.multiplatform.ui.welcome.terms

data class TermsConfirmationState(
    val isLoading: Boolean = false,
    var isSynchronizationDialogVisible: Boolean = false
)
