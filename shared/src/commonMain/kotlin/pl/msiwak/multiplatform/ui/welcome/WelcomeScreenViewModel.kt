package pl.msiwak.multiplatform.ui.welcome

import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class WelcomeScreenViewModel(
    private val navigator: Navigator,
) : ViewModel() {

    fun onRegistrationClicked() {
        navigator.navigate(NavigationDirections.Registration)
    }

    fun onLoginClicked() {
        navigator.navigate(NavigationDirections.Login)
    }
}