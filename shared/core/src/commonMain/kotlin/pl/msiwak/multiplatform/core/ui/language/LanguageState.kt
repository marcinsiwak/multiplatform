package pl.msiwak.multiplatform.core.ui.language

import pl.msiwak.multiplatform.commonObject.Language

data class LanguageState(
    val languages: List<Language> = listOf(
        Language("English", "en", false),
        Language(name = "Polski", "pl", false)
    ),
)