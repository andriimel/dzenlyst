package com.am.dzenlyst.ui.screens.Coach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.ui.Alignment
import com.am.dzenlyst.ui.components.PrimaryButton


@Composable
fun CoachScreen(viewModel: CoachViewModel = hiltViewModel()) {
    val prompt = viewModel.prompt
    val result = viewModel.result
    val isLoading = viewModel.isLoading

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("AI Coach", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = prompt,
                onValueChange = viewModel::onPromptChange,
                label = { Text("Ask something...") },
                modifier = Modifier.fillMaxWidth()
            )

            PrimaryButton(text = "Ask",
                onClick = viewModel::askGemini,
                modifier = Modifier.align(Alignment.End)
                )


            if (result.isNotBlank()) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = result,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}