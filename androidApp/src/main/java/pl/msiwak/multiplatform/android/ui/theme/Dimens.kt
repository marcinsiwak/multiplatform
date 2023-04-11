package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val space_0: Dp = 0.dp,
    val space_1: Dp = 1.dp,
    val space_2: Dp = 2.dp,
    val space_4: Dp = 4.dp,
    val space_8: Dp = 8.dp,
    val space_12: Dp = 12.dp,
    val space_16: Dp = 16.dp,
    val space_24: Dp = 24.dp,
    val space_32: Dp = 32.dp,
    val space_36: Dp = 36.dp,
    val space_64: Dp = 64.dp,
    val space_80: Dp = 80.dp,
    val space_96: Dp = 96.dp,
    val space_132: Dp = 132.dp,
    val space_164: Dp = 164.dp,
    val space_264: Dp = 264.dp,
)