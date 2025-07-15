package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.R
import com.am.dzenlyst.data.task.TaskEntity
import com.am.dzenlyst.data.task.TaskPriority
@Composable
fun TaskItem(
    task: TaskEntity,
    onToggle: () -> Unit
) {
    val priorityColor = when (task.priority) {
        TaskPriority.High -> Color(0xFFFFCCBC) // light red-orange
        TaskPriority.Normal -> Color.Transparent
        TaskPriority.Low -> Color(0xFFB2EBF2) // light blue
    }

    val priorityTextColor = when (task.priority) {
        TaskPriority.High -> Color(0xFFD84315)
        TaskPriority.Normal -> Color.Gray
        TaskPriority.Low -> Color(0xFF006064)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = { onToggle() }
                )
                Text(
                    text = task.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            if (task.priority != TaskPriority.Normal) {
                Surface(
                    color = priorityColor,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = task.priority.name,
                        color = priorityTextColor,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        }
        Divider(color = Color(0xFFE0E0E0))
    }
}