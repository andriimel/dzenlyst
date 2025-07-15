package com.am.dzenlyst.ui.screens.PomodoScreen


import com.am.dzenlyst.R
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
@Composable
fun PomodoroTimer(
    timeLeft: String,
    progress: Float,
    phase: PomodoroPhase
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        )
    )

    val progressColor = when(phase) {
        PomodoroPhase.Work -> colorResource(R.color.focusBlueLight)
        PomodoroPhase.ShortBreak -> colorResource(R.color.chartBarColor)
        PomodoroPhase.LongBreak -> colorResource(R.color.focusBlueLight)
    }

    Box(
        modifier = Modifier.size(220.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = animatedProgress,
            strokeWidth = 8.dp,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationZ = 180f }, // reverse
            color = progressColor
        )

        Text(
            text = timeLeft,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}