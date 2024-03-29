package pl.msiwak.multiplatform.ui.forceUpdate

import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator

class ForceUpdateViewModel(private val navigator: Navigator) : ViewModel() {

    fun onUpdateClicked() {
        navigator.navigate(NavigationDirections.OpenStore)
    }
}
