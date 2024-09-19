package pl.msiwak.multiplatform.ui.language

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
        val languages = viewState.value.languages.map {
            if (it.code == language) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _viewState.update { it.copy(languages = languages) }
    }

    fun onUiAction(action: LanguageUiAction) {
        when (action) {
            is LanguageUiAction.OnLanguageChanged -> onLanguageChanged(action.pos)
        }
    }

    private fun onLanguageChanged(pos: Int) {
        val newLanguages = viewState.value.languages.mapIndexed { index, language ->
            if (pos == index) {
                setLanguageUseCase(language.code)
                language.copy(isSelected = true)
            } else {
                language.copy(isSelected = false)
            }
        }
        _viewState.update { it.copy(languages = newLanguages) }
    }
}
