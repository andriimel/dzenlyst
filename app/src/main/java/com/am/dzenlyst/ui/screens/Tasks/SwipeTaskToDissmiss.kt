@file:OptIn(ExperimentalMaterialApi::class)

package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.am.dzenlyst.data.local.task.TaskEntity

@Composable
fun SwipeTaskItem(
    task: TaskEntity,
    onDelete: () -> Unit,
    onToggle: () -> Unit
) {
    val dismissState = rememberDismissState()

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == DismissValue.DismissedToStart) {
            onDelete()
        }
    }

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = { /* optional background */ },
        dismissContent = {
            TaskItem(task = task, onToggle = onToggle)
        }
    )
}