package pl.msiwak.multiplatform.android.ui.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.msiwak.multiplatform.android.ui.theme.color
import pl.msiwak.multiplatform.android.ui.theme.dimens

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.color.ShadowColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(MaterialTheme.dimens.loader_size),
            color = MaterialTheme.colorScheme.onTertiary,
            trackColor = MaterialTheme.colorScheme.tertiary,
        )
    }
}
