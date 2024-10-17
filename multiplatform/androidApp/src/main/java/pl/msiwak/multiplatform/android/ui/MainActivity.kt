package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.MainViewModel

@Suppress("LongMethod")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewState = viewModel.viewState.collectAsState()
            installSplashScreen()
                .setKeepOnScreenCondition {
                    viewState.value.isLoading
                }

            MainView()
        }
    }
}
