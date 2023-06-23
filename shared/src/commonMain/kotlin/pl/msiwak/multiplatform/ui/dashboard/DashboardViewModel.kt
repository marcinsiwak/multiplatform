package pl.msiwak.multiplatform.ui.dashboard

import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.user.GetUserUseCase

class DashboardViewModel(private val getUser: GetUserUseCase) : ViewModel() {

    init {
        viewModelScope.launch {
            val user = getUser()
            Napier.e("OUTPUT, ${user.email}")
        }
    }
}