package pl.msiwak.multiplatform.android.ui.theme

import androidx.annotation.Dimension
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val fontSize = FontSize()
val dimension = Dimension()

val Typography = Typography(
    titleLarge = TextStyle(
        fontSize = fontSize.font_24,
        letterSpacing = 0.3.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle(
        fontSize = fontSize.font_16,
        letterSpacing = 0.3.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle(
        fontSize = fontSize.font_16,
        letterSpacing = 0.3.sp,
        lineHeight = 24.sp
    ),
    labelMedium = TextStyle(
        fontSize = fontSize.font_12,
        letterSpacing = 0.3.sp,
        lineHeight = 16.sp
    ),
    bodyLarge = TextStyle(
        fontSize = fontSize.font_12,
        letterSpacing = 0.3.sp,
        lineHeight = 16.sp
    ),
    bodyMedium = TextStyle(
        fontSize = fontSize.font_12,
        letterSpacing = 0.3.sp,
        lineHeight = 16.sp
    )
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
)
