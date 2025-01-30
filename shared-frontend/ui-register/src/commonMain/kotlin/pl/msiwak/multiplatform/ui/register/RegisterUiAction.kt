package pl.msiwak.multiplatform.ui.register

sealed class RegisterUiAction {
    data class OnLoginChanged(val login: String) : RegisterUiAction()
    data class OnPasswordChanged(val password: String) : RegisterUiAction()
    data object OnVisibilityClicked : RegisterUiAction()
    data object OnRegisterClicked : RegisterUiAction()
    data class OnTermsClicked(val isChecked: Boolean) : RegisterUiAction()
}
