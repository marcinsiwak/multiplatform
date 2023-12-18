package pl.msiwak.multiplatform.ui.forceUpdate

import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class ForceUpdateViewModel(private val navigator: Navigator) : ViewModel() {

    fun onUpdateClicked() {
        navigator.navigate(NavigationDirections.OpenStore)
    }
}
