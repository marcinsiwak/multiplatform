package pl.msiwak.multiplatform.android.ui.language

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import pl.msiwak.multiplatform.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.ui.language.LanguageViewModel

@Composable
fun LanguageScreen() {
    val viewModel = koinViewModel<LanguageViewModel>()
    val state = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Text(
            modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_16, horizontal = MaterialTheme.dimens.space_24),
            text = stringResource(MR.strings.language.resourceId),
            fontSize = MaterialTheme.font.font_24,
            color = Color.White
        )

        LazyColumn {
            itemsIndexed(state.value.languages) { index, item ->
                Row {
                    RadioButton(
                        selected = item.isSelected,
                        onClick = {
                            viewModel.onLanguageChanged(index)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = item.name,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}