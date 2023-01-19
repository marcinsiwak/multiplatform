package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.register.RegisterScreen
import pl.msiwak.multiplatform.android.ui.theme.BaseKmm_ProjectTheme
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreen
import pl.msiwak.multiplatform.android.navigation.NavigationDirections

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseKmm_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationDirections.Welcome.destination
                    ) {
                        composable(NavigationDirections.Welcome.destination) { WelcomeScreen() }
                        composable(NavigationDirections.Registration.destination) { RegisterScreen() }

                    }

                    viewModel.mainNavigator.commands.collectAsState().value.also { command ->
                        if (command.destination.isNotEmpty()) {
                            navController.navigate(command.destination)
                        }
                    }
                }
            }
        }
    }
}
