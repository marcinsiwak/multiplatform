package pl.msiwak.multiplatform.android.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import io.github.aakira.napier.Napier
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.navigator.NavigationDirections
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

    private fun navigate(
        navController: NavController,
        command: NavigationDirections
    ) {
        if (command.isInclusive) {
            navController.navigate(route = command.destination) {
                popUpTo(0)
            }
        } else {
            navController.navigate(route = command.destination)
        }
    }

    private fun openStore() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$URI_MARKET$packageName")))
        } catch (e: ActivityNotFoundException) {
            Napier.e("Error: $e")
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$URI_STORE$packageName")
                )
            )
        }
    }

    companion object {
        private const val URI_MARKET = "market://details?id="
        private const val URI_STORE = "https://play.google.com/store/apps/details?id="
    }
}
