package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.MainViewModel
import pl.msiwak.multiplatform.ui.commonComponent.extension.lifecycleCallback

@Suppress("LongMethod")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    private lateinit var lifeCycleObserver: LifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateLifecycleObserver()
//        NotificationsService()

        setContent {
            val viewState = viewModel.viewState.collectAsState()
            installSplashScreen()
                .setKeepOnScreenCondition {
                    viewState.value.isLoading
                }
            MainView()
        }
    }

    private fun initiateLifecycleObserver() {
        lifeCycleObserver = LifecycleEventObserver { _, event ->
            lifecycleCallback(event)
        }
        lifecycle.addObserver(lifeCycleObserver)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(lifeCycleObserver)
        super.onDestroy()
    }
}
