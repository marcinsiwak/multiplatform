package pl.msiwak.multiplatform.ui.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.terms
import athletetrack.shared_mobile.commonresources.generated.resources.terms_and_conditions
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun TermsScreen(navController: NavController) {
    TermsScreenContent(navController)
}

@Composable
private fun TermsScreenContent(navController: NavController) {
    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.terms))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(top = it.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {
                HtmlText(
                    modifier = Modifier.fillMaxSize(),
                    text = stringResource(Res.string.terms_and_conditions)
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
private fun TermsScreenPreview() {
    AppTheme {
        TermsScreenContent(rememberNavController())
    }
}
