package com.am.dzenlyst.ui.screens.PomodoScreen


import com.am.dzenlyst.R
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.dzenlyst.ui.utils.OrbitronFont


@Composable
fun PomodoroTimer(
    timeLeft: String,
    progress: Float,
    phase: PomodoroPhase
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    val progressColor = when (phase) {
        PomodoroPhase.Work -> colorResource(R.color.progressBarWorkColor)
        PomodoroPhase.ShortBreak, PomodoroPhase.LongBreak -> colorResource(R.color.progressBarRestColor)
    }



    Box(
        modifier = Modifier.size(220.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = 1f,
            strokeWidth = 12.dp,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationZ = 180f },
            color = colorResource(R.color.progressBarBgColor)
        )
        CircularProgressIndicator(
            progress = animatedProgress,
            strokeWidth = 12.dp,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationZ = 180f },
            color = progressColor
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = timeLeft,
                fontWeight = FontWeight.Bold,
                fontFamily = OrbitronFont,
                fontSize = 50.sp,
            )

            Icon(

                painter = painterResource(
                    id = when (phase) {
                        PomodoroPhase.Work -> R.drawable.ic_clock
                        else -> R.drawable.ic_coffee_24
                    }
                ),
                contentDescription = null,
                tint = progressColor,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}