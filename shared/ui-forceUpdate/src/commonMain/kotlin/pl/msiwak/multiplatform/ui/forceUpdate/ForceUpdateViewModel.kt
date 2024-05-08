package pl.msiwak.multiplatform.ui.forceUpdate

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator

@Stable
class ForceUpdateViewModel(private val navigator: Navigator) : ViewModel() {

    fun onUpdateClicked() {
        navigator.navigate(NavigationDirections.OpenStore)
    }
}
