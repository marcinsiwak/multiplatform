package pl.msiwak.multiplatform.ui.forceUpdate

import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator

class ForceUpdateViewModel(private val navigator: Navigator) : ViewModel() {

    fun onUpdateClicked() {
        navigator.navigate(NavigationDirections.OpenStore)
    }
}
