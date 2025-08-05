package com.am.dzenlyst.ui.screens.PomodoScreen


import com.am.dzenlyst.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.ui.screens.Tasks.TaskViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.data.local.task.TaskPriority
import com.am.dzenlyst.ui.screens.PomodoScreen.FocusInfo.FocusInfoDialog
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroModeSelectorDialog
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.AddTaskSheetContent
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.TaskBottomSheet
import com.am.dzenlyst.ui.screens.TaskDetailsScreen.TaskDetailsDialogue
import com.am.dzenlyst.ui.utils.MontserratFont
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(pomodoroViewModel: PomodoroViewModel = hiltViewModel(),
                   taskViewModel: TaskViewModel = hiltViewModel()) {

    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }
    val selectedTaskId = selectedTask?.id

    val keyboardController = LocalSoftwareKeyboardController.current


    val timeLeft = pomodoroViewModel.timeLeft.collectAsState().value
    val progress = pomodoroViewModel.progress.collectAsState().value
    val phase = pomodoroViewModel.phase.collectAsState().value


    val topTasks by taskViewModel.topTasks.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    var inputText by remember { mutableStateOf("") }

    var showInfoDialog by remember { mutableStateOf(false) }
    var showModeDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {

        FocusInfoDialog(onDismiss = { showInfoDialog = false })
    }



    if (showModeDialog) {
        PomodoroModeSelectorDialog(
            currentMode = pomodoroViewModel.selectMode.value,
            onSelect = { mode ->
                pomodoroViewModel.onModeSelected(mode)
                showModeDialog = false
                pomodoroViewModel.resetPhaseToWork()
            },
            onDismiss = { showModeDialog = false }
        )
    }

    if (showSheet) {
        TaskBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                    showSheet = false
                }
            }
        ) {
            AddTaskSheetContent(
                input = inputText,
                onInputChange = { inputText = it },
                showPriorityTable = false,
                selectedPriority = TaskPriority.Normal,
                onPriorityChange = {/* TODO*/ },
                onAddClick = {
                    coroutineScope.launch {
                        if (inputText.isNotBlank()) {

                            selectedTaskId?.let { taskId ->
                                taskViewModel.addSubtask(taskId, inputText)
                            }
                            keyboardController?.hide()
                            inputText = ""
                        }
                        sheetState.hide()
                        showSheet = false
                    }
                }
            )
        }
    }



    selectedTask?.let { task ->
        TaskDetailsDialogue(
            task = task,
            onEditClick = {
                showSheet = true
            },
            onDismiss = { selectedTask = null },
            viewModel = taskViewModel
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        FocusHeader(
            onInfoClick = { showInfoDialog = true },
            onModeClick = { showModeDialog = true })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("DzenLyst",
                color = colorResource(id = R.color.mainTitleColor),
                fontFamily = MontserratFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 56.sp
            )
            Text(
                text = when (phase) {
                    PomodoroPhase.Work -> "Work Session"
                    PomodoroPhase.ShortBreak -> "Short Break"
                    PomodoroPhase.LongBreak -> "Long Break"
                },
                fontFamily = MontserratFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.regularTextColor)
            )
            Spacer(modifier = Modifier.height(32.dp))
            PomodoroTimer(timeLeft = timeLeft, progress = progress, phase = phase)
            PomodoroControls(viewModel = pomodoroViewModel)
            Spacer(modifier = Modifier.height(32.dp))
            FocusTaskList(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tasks = topTasks
            ) { taskClicked ->
                selectedTask = taskClicked
            }

        }
    }
}
