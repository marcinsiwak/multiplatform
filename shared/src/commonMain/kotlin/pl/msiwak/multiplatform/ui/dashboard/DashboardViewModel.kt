package pl.msiwak.multiplatform.ui.dashboard

import io.github.aakira.napier.Napier
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.SaveUserTokenUseCase

class DashboardViewModel(
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {

    init {
        saveUserTokenUseCase("TOKEN")
        val token = getUserTokenUseCase()
        Napier.e("OUTPUT, $token")
    }

}