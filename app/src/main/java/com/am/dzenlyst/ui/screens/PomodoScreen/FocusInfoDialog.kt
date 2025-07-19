package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.ui.components.PrimaryButton

@Composable
fun FocusInfoDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
        title = {
            Text("Pomodoro Modes")
        },
        text = {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Classic Pomodoro:")
                Text("- 25 min work, 5 min short break, 15 min long break\n")
                Text("Extended Focus:")
                Text("- 50 min work, 10 min break\n")
                Text("Ultra-Short Sprints:")
                Text("- 15 min work, 3 min break\n")
                Text("Flow-Based:")
                Text("- Variable work time based on flow state\n")
                Text("Balanced Focus:")
                Text("- 35 min work, 7 min break\n")
                Text("Adaptive:")
                Text("- Dynamic intervals based on performance\n")
            }
        },
        confirmButton = {
            PrimaryButton(text = "Close", onClick = onDismiss, filled = true )
            
        }
    )
}