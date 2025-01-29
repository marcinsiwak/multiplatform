package pl.msiwak.multiplatform.ui.adminpanel

import pl.msiwak.multiplatform.commonObject.User

data class AdminPanelState(
    val users: List<User> = emptyList()
)
