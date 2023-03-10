package pl.msiwak.multiplatform.ui.summary

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SummaryDiHelper : KoinComponent {
    private val summaryVM: SummaryViewModel by inject()
    fun getSummaryViewModel() = summaryVM
}