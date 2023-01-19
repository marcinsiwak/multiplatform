package pl.msiwak.multiplatform.android.ui

import androidx.lifecycle.ViewModel
import pl.msiwak.multiplatform.android.navigation.Navigator

class MainViewModel(private val navigator: Navigator) : ViewModel() {

    val mainNavigator = navigator
}