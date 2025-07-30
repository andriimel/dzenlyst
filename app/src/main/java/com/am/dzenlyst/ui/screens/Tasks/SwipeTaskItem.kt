@file:OptIn(ExperimentalMaterialApi::class)

package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.ui.components.DeleteBg

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
        background = {

            DeleteBg()
        },
        dismissContent = {
            TaskItem(task = task, onToggle = onToggle, onClick = {onClick(task)})
        }
    )
}