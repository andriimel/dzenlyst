package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.ui.components.PrimaryButton

@Composable
fun PomodoroControls(viewModel: PomodoroViewModel){
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 24.dp)
    ) {
        PrimaryButton(text = "Start", onClick = {viewModel.startTimer()}, filled = true)
        PrimaryButton(text = "Pause", onClick = {viewModel.pauseTimer()}, filled = false)
        PrimaryButton(text = "Reset", onClick = {viewModel.resetTimer()}, filled = false)

    }
}