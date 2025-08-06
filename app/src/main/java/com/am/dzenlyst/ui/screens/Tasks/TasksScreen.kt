package com.am.dzenlyst.ui.screens.Tasks

import com.am.dzenlyst.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.data.local.task.TaskPriority
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.AddTaskSheetContent
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.TaskBottomSheet
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.TaskDetailsDialogue
import com.am.dzenlyst.ui.utils.MontserratFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    var selectedTask by remember {mutableStateOf<TaskEntity?>(null)}

    val selectedTasksId = selectedTask?.id

    var showSheet by remember { mutableStateOf(false) }
    var showPriorityTable by remember { mutableStateOf(false)}
    var inputText by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(TaskPriority.Normal) }

    var showDialog by remember { mutableStateOf(false) }

    Scaffold {
        padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Tasks",
                color = colorResource(id = R.color.mainTitleColor),
                fontFamily = MontserratFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 56.sp )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                shape = RoundedCornerShape(16.dp),

                ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks, key = { it.id }) { task ->

                        SwipeTaskItem(
                            task = task,
                            onToggle = { viewModel.toggleDone(task) },
                            onDelete = { viewModel.deleteTask(task)
                                       showDialog = true},
                            onClick = {selectedTask = it}
                            )
                    }
                }
            }
        }
            selectedTask?.let { task ->
                TaskDetailsDialogue(task = task,
                    onEditClick = { showSheet = true
                                  showPriorityTable = false},
                    onDismiss = {selectedTask = null},
                    viewModel)

            }

            if (showDialog){
                ConfirmDeleteDialog(onConfirm = {
                    showDialog = false
                    viewModel.incrementConfirmedProjects()
                },
                    onDismiss = {
                        showDialog = false
                    })
            }

            LaunchedEffect(showSheet) {
                if (showSheet) {
                    inputText = ""
                    selectedPriority = TaskPriority.Normal
                    sheetState.show()
                } else {
                    sheetState.hide()
                }
            }

            if(!showSheet){
                FloatingActionButton(
                    onClick = {
                        showSheet = true
                        showPriorityTable = true
                    },
                    shape = CircleShape,
                    containerColor = colorResource(R.color.focusBlueLight),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }else{
                TaskBottomSheet(sheetState = sheetState,
                    onDismissRequest = {
                        coroutineScope.launch {
                            sheetState.hide()
                            showSheet = false
                        }
                    }) {
                    AddTaskSheetContent(
                        input = inputText,
                        onInputChange = {inputText = it},
                        showPriorityTable = showPriorityTable,
                        selectedPriority = selectedPriority,
                        onPriorityChange = {selectedPriority = it},
                        onAddClick = {
                            coroutineScope.launch {
                                if (inputText.isNotBlank()){
                                    if (showPriorityTable) {
                                        viewModel.addTask(inputText, selectedPriority)
                                    } else {
                                       selectedTasksId?.let { taskId->
                                           viewModel.addSubtask(taskId, inputText)
                                       }
                                    }
                                    inputText = ""
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

