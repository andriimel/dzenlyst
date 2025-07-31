package com.am.dzenlyst.ui.screens.Tasks.Subtasks

import com.am.dzenlyst.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.am.dzenlyst.data.local.task.Subtasks.SubtaskEntity
import com.am.dzenlyst.ui.components.PrimaryCheckbox


@Composable
fun SubtaskItem(subtask: SubtaskEntity,
                onToggle: () -> Unit,
                ) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        //shape = RoundedCornerShape(16.dp),
        //elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            PrimaryCheckbox(checked = subtask.isDone,
                onCheckedChange = {onToggle()},
                modifier = Modifier
                    .size(20.dp)
                    .padding(4.dp))


            Text(
                text = subtask.text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}