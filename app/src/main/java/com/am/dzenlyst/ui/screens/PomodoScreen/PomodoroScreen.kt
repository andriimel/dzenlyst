package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PomodoroScreen() {

    val viewModel: PomodoroViewModel = hiltViewModel()
    val timeLeft = viewModel.timeLeft.collectAsState().value
    val progress = viewModel.progress.collectAsState().value
    val phase = viewModel.phase.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("DzenLyst", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = when(phase){
                    PomodoroPhase.Work -> "Work Session"
                    PomodoroPhase.ShortBreak -> "Short Break"
                    PomodoroPhase.LongBreak ->  "Long Break"
                },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(32.dp))
            PomodoroTimer(timeLeft = timeLeft,progress = progress, phase = phase )
            PomodoroControls(viewModel = viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            TaskList( modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        //BottomNavBar()
    }
}
