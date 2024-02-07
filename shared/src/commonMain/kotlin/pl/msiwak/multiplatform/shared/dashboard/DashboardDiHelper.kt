package pl.msiwak.multiplatform.shared.dashboard

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel

class DashboardDiHelper : KoinComponent {
    private val dashboardVM: DashboardViewModel by inject()
    fun getDashboardViewModel() = dashboardVM
}
