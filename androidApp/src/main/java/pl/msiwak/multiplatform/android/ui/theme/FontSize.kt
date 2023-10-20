package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val LocalFont = staticCompositionLocalOf { FontSize() }

data class FontSize(
    val font_8: TextUnit = 8.sp,
    val font_12: TextUnit = 12.sp,
    val font_14: TextUnit = 14.sp,
    val font_16: TextUnit = 16.sp,
    val font_20: TextUnit = 20.sp,
    val font_24: TextUnit = 24.sp,
)

val MaterialTheme.font: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFont.current
