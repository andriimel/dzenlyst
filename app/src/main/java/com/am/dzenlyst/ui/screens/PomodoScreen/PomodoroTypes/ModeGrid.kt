package com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ModeGrid(
    currentMode: PomodoroMode,
    onSelect: (PomodoroMode) -> Unit
) {
    val modes = PomodoroModes.take(4)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
    ) {
        for (row in 0..1) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                for (col in 0..1) {
                    val index = row * 2 + col
                    val mode = modes.getOrNull(index)
                    if (mode != null) {
                        ModeItem(
                            mode = mode,
                            isSelected = mode == currentMode,
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            onClick = { onSelect(mode) }
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
