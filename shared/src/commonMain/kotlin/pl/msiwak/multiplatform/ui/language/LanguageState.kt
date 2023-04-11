package pl.msiwak.multiplatform.ui.language

import pl.msiwak.multiplatform.data.common.Language

data class LanguageState(
    val languages: List<Language> = listOf(
        Language("English", "en", false),
        Language(name = "Polski", "pl", false)
    ),
)