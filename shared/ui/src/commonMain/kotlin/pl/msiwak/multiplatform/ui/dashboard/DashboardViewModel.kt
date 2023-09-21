package pl.msiwak.multiplatform.ui.dashboard

import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.user.GetUserUseCase

class DashboardViewModel(
    private val getUser: GetUserUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {


    init {
        viewModelScope.launch(globalErrorHandler.handleError()) {
            val user = getUser()
            Napier.e("OUTPUT, ${user.email}")
        }
    }
}