package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.dzenlyst.R
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.ui.utils.MontserratFont

@Composable
fun FocusTaskList(
    modifier: Modifier = Modifier,
    tasks: List<TaskEntity>,
    onClick: (TaskEntity) -> Unit
) {
    if (tasks.isEmpty()) return
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Task:",
                fontFamily = MontserratFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = colorResource(id = R.color.regularTextColor))
            tasks.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick(task) }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                        Checkbox(
                            checked = task.isDone,
                            onCheckedChange = {}
                        )
                        Text(
                            text = task.text,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = MontserratFont,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.regularTextColor),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .weight(1f),
                            )
                    }
//
//                    Text(
//                        text = "(${task.priority.name})",
//                        style = MaterialTheme.typography.bodySmall,
//                        modifier = Modifier.padding(start = 8.dp)
//                    )
                }
            }
        }
}