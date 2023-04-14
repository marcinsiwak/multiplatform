package pl.msiwak.multiplatform.ui.language

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.settings.SetLanguageUseCase

class LanguageViewModel(
    getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(LanguageState())
    val viewState: StateFlow<LanguageState> = _viewState

    init {
        val language = getLanguageUseCase()
        val languages = _viewState.value.languages.map {
            if (it.code == language) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _viewState.value = _viewState.value.copy(languages = languages)
    }

    fun onLanguageChanged(pos: Int) {
        val newLanguages = _viewState.value.languages.mapIndexed { index, language ->
            if (pos == index) {
                setLanguageUseCase(language.code)
                language.copy(isSelected = true)
            } else {
                language.copy(isSelected = false)
            }
        }
        _viewState.value = _viewState.value.copy(languages = newLanguages)
    }
}