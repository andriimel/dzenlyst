package com.am.dzenlyst.ui.screens.PomodoScreen.FocusInfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.dzenlyst.ui.components.PrimaryButton
import com.am.dzenlyst.ui.utils.MontserratFont

@Composable
fun FocusInfoDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        title = {
            Text(
                text = "Pomodoro Modes",
                fontFamily = MontserratFont,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        text = {
            Column(modifier = Modifier.padding(8.dp)) {
                ModeDescription(
                    title = "🟥 Classic",
                    description = "25 min focus • 5 min break • 15 min long break after 4 cycles.\nGood for beginners and standard tasks."
                )
                Spacer(Modifier.height(8.dp))
                ModeDescription(
                    title = "🟦 Extended Focus",
                    description = "50 min focus • 10 min break • 25 min long break after 2 cycles.\nBest for deep work and experienced users."
                )
                Spacer(Modifier.height(8.dp))
                ModeDescription(
                    title = "🟩 Sprint",
                    description = "10 min focus • 2 min break • 10 min long break after 5 cycles.\nGreat for quick starts or ADHD-friendly workflows."
                )
                Spacer(Modifier.height(8.dp))
                ModeDescription(
                    title = "🟨 Balanced",
                    description = "40 min focus • 10 min break • 20 min long break after 3 cycles.\nA universal choice balancing depth and recovery."
                )
            }
        },
        confirmButton = {
            PrimaryButton(text = "Close", onClick = onDismiss, filled = true)
        }
    )
}

@Composable
private fun ModeDescription(title: String, description: String) {
    Column {
        Text(
            text = title,
            fontFamily = MontserratFont,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = description,
            fontFamily = MontserratFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}