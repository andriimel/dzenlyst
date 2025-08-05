package com.am.dzenlyst.ui.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.am.dzenlyst.R

val OrbitronFont = FontFamily(
    Font(R.font.orbitron_regular, FontWeight.Normal),
    Font(R.font.orbitron_regular, FontWeight.Bold)
)

val MontserratFont = FontFamily(
    Font(R.font.montserrat_regular_wght, FontWeight.Normal),
    Font(R.font.montserrat_italic, FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.montserrat_title, FontWeight.SemiBold),
    Font(R.font.montserrat_regular_text_font, FontWeight.Medium)
)