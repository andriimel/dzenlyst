package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.ui.components.PrimaryButton

@Composable
fun PomodoroControls(viewModel: PomodoroViewModel){
    val isRunning = viewModel.isRunningFlow.collectAsState().value
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 24.dp)
    ) {
        PrimaryButton(text = "Start", onClick = {viewModel.startTimer()}, filled = !isRunning)
        PrimaryButton(text = "Pause", onClick = {viewModel.pauseTimer()}, filled = isRunning)
        PrimaryButton(text = "Reset", onClick = {viewModel.resetTimer()}, filled = false)

    }
}