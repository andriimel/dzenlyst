package com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.R

@Composable
fun ModeItem(
    mode: PomodoroMode,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.focusBlueLight)
    } else {
        colorResource(id = R.color.focusBlueLight).copy(alpha = 0.1f)
    }

    val textColor = if (isSelected) {
        Color.White
    } else {
        colorResource(id = R.color.focusBlueLight)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mode.type.name.replace("_", " ")
                .lowercase()
                .replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.body1,
            color = textColor
        )
    }
}
