package com.example.shorts.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shorts.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(

        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.kanit_cyrillic))
    ),
    caption = TextStyle(
        color = Color.DarkGray,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.kanit_cyrillic))
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val cyrillic = FontFamily(Font(R.font.kanit_cyrillic))