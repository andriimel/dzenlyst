package com.am.dzenlyst.ui.screens.TaskDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.ui.screens.Tasks.TaskViewModel
import androidx.compose.runtime.*
import com.am.dzenlyst.ui.screens.Tasks.Subtasks.SubtaskItem
import com.am.dzenlyst.ui.screens.Tasks.Subtasks.SwipeSubtaskItem

@Composable
fun TaskDetailsDialogue(
    task: TaskEntity,
    onEditClick: () -> Unit,
    onDismiss: () -> Unit,
    viewModel: TaskViewModel
) {
    val subtasks by viewModel.subtasks.collectAsState()
    LaunchedEffect(task.id) {
        viewModel.getSubtasksForTask(task.id)
    }
    Dialog(onDismissRequest = onDismiss) {

        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier

                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            color = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = task.text,
                        style = MaterialTheme.typography.h6
                    )
//                    Text(
//                        text = task.priority.name,
//                        style = MaterialTheme.typography.h6,
//                        color = when (task.priority) {
//                            TaskPriority.High -> Color.Red
//                            TaskPriority.Low -> Color.Gray
//                            else -> Color.Black
//                        }
//                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(subtasks, key = { it.id }) { subtask ->
                        SwipeSubtaskItem(
                            subtask = subtask,
                            onDelete = { viewModel.deleteSubtask(subtask) },
                            onToggle = { viewModel.toggleSubtask(subtask) }
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // TODO: Subtasks section

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                ) {
                    TextButton(onClick = onEditClick) {
                        Text("Edit")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = onDismiss) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
