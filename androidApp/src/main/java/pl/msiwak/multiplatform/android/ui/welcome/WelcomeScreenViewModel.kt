package pl.msiwak.multiplatform.android.ui.welcome

import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.android.navigation.NavigationDirections
import pl.msiwak.multiplatform.android.navigation.Navigator

class WelcomeScreenViewModel(private val navigator: Navigator) : ViewModel() {

    fun onRegistrationClicked() {
        navigator.navigate(NavigationDirections.Registration)
    }

    fun onLoginClicked() {
        navigator.navigate(NavigationDirections.Login)
    }
}