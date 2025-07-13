package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PomodoroTimer(){
    Box(
        modifier = Modifier
            .size(220.dp), // circle
        contentAlignment = Alignment.Center
    ) {
        // circle
        CircularProgressIndicator(
            progress = 1f,
            strokeWidth = 8.dp,
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        )


        Text(
            text = "25:00",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}