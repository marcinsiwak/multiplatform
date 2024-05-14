package pl.msiwak.multiplatform.ui.dashboard

import pl.msiwak.multiplatform.ui.settings.SettingsGraph
import pl.msiwak.multiplatform.ui.summary.SummaryGraph

data class BottomNavigationProvider(
    val summaryGraph: SummaryGraph,
    val settingsGraph: SettingsGraph
)
