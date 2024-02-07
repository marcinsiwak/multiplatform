package pl.msiwak.multiplatform.shared.summary

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel

class SummaryDiHelper : KoinComponent {
    private val summaryVM: SummaryViewModel by inject()
    fun getSummaryViewModel() = summaryVM
}
