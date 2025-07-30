package com.am.dzenlyst.ui.screens.TaskDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.am.dzenlyst.data.local.task.TaskPriority
import com.am.dzenlyst.ui.components.PrimaryButton
import com.am.dzenlyst.ui.screens.Tasks.PriorityDropdown

@Composable
fun AddTaskSheetContent(
    input: String,
    onInputChange: (String) -> Unit,
    showPriorityTable: Boolean,
    selectedPriority: TaskPriority,
    onPriorityChange: (TaskPriority) -> Unit,
    onAddClick: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            placeholder = { Text("New task") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showPriorityTable) {
                PriorityDropdown(
                    selected = selectedPriority,
                    onChange = onPriorityChange
                )
            }

            PrimaryButton(
                text = "Add",
                onClick = onAddClick
            )
        }
    }
}