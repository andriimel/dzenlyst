@file:OptIn(ExperimentalMaterialApi::class)

package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.am.dzenlyst.data.local.task.TaskEntity

@Composable
fun SwipeTaskItem(
    task: TaskEntity,
    onDelete: () -> Unit,
    onToggle: () -> Unit,
    onClick: (TaskEntity) -> Unit
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
        background = { },
        dismissContent = {
            TaskItem(task = task, onToggle = onToggle, onClick = {onClick(task)})
        }
    )
}