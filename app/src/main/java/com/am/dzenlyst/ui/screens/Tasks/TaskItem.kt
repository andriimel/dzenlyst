package com.am.dzenlyst.ui.screens.Tasks

import androidx.compose.foundation.clickable
import com.am.dzenlyst.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.data.local.task.TaskPriority
import com.am.dzenlyst.ui.components.PrimaryCheckbox

@Composable
fun TaskItem(
    task: TaskEntity,
    onToggle: () -> Unit,
    onClick:() -> Unit
) {
    val priorityColor = when (task.priority) {
        TaskPriority.High -> colorResource(id = R.color.taskPriorityHighBgColor)
        TaskPriority.Normal -> colorResource(id = R.color.taskPriorityNormalBgColor)
        TaskPriority.Low -> colorResource(id = R.color.taskPriorityLowBgColor)
    }

    val priorityTextColor = when (task.priority) {
        TaskPriority.High -> colorResource(id = R.color.taskPriorityHighTextColor)
        TaskPriority.Normal -> Color.Gray
        TaskPriority.Low -> colorResource(id = R.color.taskPriorityLowTextColor)
    }

    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick = { onClick()}),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically) {
//                Checkbox(
//                    checked = task.isDone,
//                    onCheckedChange = { onToggle() }
//                )
                PrimaryCheckbox(checked = task.isDone,
                    onCheckedChange = {onToggle()},
                    modifier = Modifier
                        .size(26.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = task.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }


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
}