package pl.msiwak.multiplatform.android.ui.welcome

import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.android.navigation.NavigationDirections
import pl.msiwak.multiplatform.android.navigation.Navigator

class WelcomeScreenViewModel(private val navigator: Navigator) : ViewModel() {

    val title = "TITLE"

    fun navigateToRegistration() {
        navigator.navigate(NavigationDirections.Registration)
    }
}