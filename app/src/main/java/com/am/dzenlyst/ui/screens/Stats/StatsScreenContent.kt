package com.am.dzenlyst.ui.screens.Stats

import com.am.dzenlyst.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatsScreenContent(
    dailySessions: List<Int>,
    focusSessions: Int,
    completedTasks: Int,
    averageMinutesFocused: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Stats",
            style = MaterialTheme.typography.headlineMedium
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                val days = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
                val max = dailySessions.maxOrNull()?.takeIf { it > 0 } ?: 1

                dailySessions.forEachIndexed { index, count ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Box(
                            modifier = Modifier
                                .height((count * 80 / max).dp)
                                .width(20.dp)
                                .background(
                                    color = colorResource(R.color.chartBarColor), // or MaterialTheme.colorScheme.primary
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = days[index],
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        StatRow(label = "Focus sessions", value = focusSessions.toString())
        StatRow(label = "Completed tasks", value = completedTasks.toString())
        StatRow(
            label = "Average time focused",
            value = "${averageMinutesFocused / 60} h ${averageMinutesFocused % 60} m"
        )
    }
}