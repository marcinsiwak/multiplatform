package pl.msiwak.multiplatform.ui.language

sealed class LanguageUiAction {
    data class OnLanguageChanged(val pos: Int) : LanguageUiAction()
}
