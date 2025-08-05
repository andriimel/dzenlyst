package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.dzenlyst.ui.components.PrimaryButton
import com.am.dzenlyst.ui.utils.MontserratFont

@Composable
fun PomodoroControls(viewModel: PomodoroViewModel){
    val isRunning = viewModel.isRunningFlow.collectAsState().value
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        PrimaryButton(
            text = "Start",
            onClick = { viewModel.startTimer() },
            filled = !isRunning,
            modifier = Modifier.weight(1f)
        )
        PrimaryButton(
            text = "Pause",
            onClick = { viewModel.pauseTimer() },
            filled = isRunning,
            modifier = Modifier.weight(1f)
        )
        PrimaryButton(
            text = "Reset",
            onClick = { viewModel.resetTimer() },
            filled = false,
            modifier = Modifier.weight(1f)
        )
    }
}