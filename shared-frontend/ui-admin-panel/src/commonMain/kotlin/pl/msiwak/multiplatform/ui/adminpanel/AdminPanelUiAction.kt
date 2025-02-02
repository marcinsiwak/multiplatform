package pl.msiwak.multiplatform.ui.adminpanel

import pl.msiwak.multiplatform.commonObject.User

sealed class AdminPanelUiAction {
    class OnUserClick(val user: User) : AdminPanelUiAction()
}
