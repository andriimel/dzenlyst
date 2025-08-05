package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmDeleteDialog(title: String = "Complete the task?",
                  onConfirm: () -> Unit,
                  onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes, I've done it! ")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No! Pls, just delete!")
            }
        },
        title = {
            Text(text = title)
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    )
}