package pl.msiwak.multiplatform.android.ui

import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.ui.navigator.Navigator

class MainViewModel(private val navigator: Navigator) : ViewModel() {

    val mainNavigator = navigator
}