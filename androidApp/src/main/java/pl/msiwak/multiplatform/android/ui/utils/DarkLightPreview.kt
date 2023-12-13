package pl.msiwak.multiplatform.android.ui.utils

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, name = "Light", group = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = UI_MODE_NIGHT_YES, group = "Dark")
annotation class DarkLightPreview
