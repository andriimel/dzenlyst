package com.am.dzenlyst.ui.screens.Tasks

import com.am.dzenlyst.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.data.task.TaskEntity
import com.am.dzenlyst.data.task.TaskPriority
import com.am.dzenlyst.ui.components.PrimaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    var showSheet by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(TaskPriority.Normal) }

    Scaffold(
        floatingActionButton = {
            if (!showSheet) {
                FloatingActionButton(
                    onClick = {
                        showSheet = true
                        coroutineScope.launch { sheetState.show() }
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Tasks", style = MaterialTheme.typography.headlineMedium)

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 350.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF5F5F5)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 350.dp)
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks, key = { it.id }) { task ->
                        SwipeTaskItem(
                            task = task,
                            onToggle = { viewModel.toggleDone(task) },
                            onDelete = { viewModel.deleteTask(task) }
                        )
                        Divider()
                    }
                }
            }
        }

        // Bottom view
        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        sheetState.hide()
                        if (input.isNotBlank()) {
                            viewModel.addTask(input, selectedPriority)
                            input = ""
                        }
                        showSheet = false
                    }
                },
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = input,
                        onValueChange = { input = it },
                        placeholder = { Text("New task") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PriorityDropdown(
                            selected = selectedPriority,
                            onChange = { selectedPriority = it }
                        )

                        PrimaryButton(
                            text = "Add",
                            onClick = {
                                coroutineScope.launch {
                                    if (input.isNotBlank()) {
                                        viewModel.addTask(input, selectedPriority)
                                        input = ""
                                    }
                                    sheetState.hide()
                                    showSheet = false
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

