package com.hamza.loudly.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextSize(
    val default: TextUnit = 16.sp,
    val extraExtraSmall: TextUnit = 10.sp,
    val extraSmall: TextUnit = 12.sp,
    val small: TextUnit = 14.sp,
    val medium: TextUnit = 18.sp,
    val large: TextUnit = 20.sp,
    val extraLarge: TextUnit = 22.sp

)


val LocalTextSize = compositionLocalOf { TextSize() }

val MaterialTheme.textSize: TextSize
    @Composable
    @ReadOnlyComposable
    get() = LocalTextSize.current