package com.example.pepcorns.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode


val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val GradientColor = Brush.linearGradient(
    colors = listOf(
        Color(0xFF1D28BA), // rgba(29,40,186,1)
        Color(0xFF1E1F60), // rgba(30,31,96,1)
        Color(0xFF21258C)  // rgba(33,37,140,1)
    ),
    start = Offset.Zero,
    end = Offset.Infinite,
    tileMode = TileMode.Clamp
)