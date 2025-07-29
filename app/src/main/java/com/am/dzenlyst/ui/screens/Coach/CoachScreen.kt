package com.am.dzenlyst.ui.screens.Coach

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.ui.Alignment
import com.am.dzenlyst.ui.components.PrimaryButton
import kotlinx.coroutines.delay


@Composable
fun CoachScreen(viewModel: CoachViewModel = hiltViewModel()) {
    val prompt = viewModel.prompt
    val result = viewModel.result
    val isLoading = viewModel.isLoading
    val scrollState = rememberScrollState()

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
                        .verticalScroll(scrollState)
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

@Composable
fun TypingEffectText(
    fullText: String,
    charDelayMillis: Long = 30,
    modifier: Modifier = Modifier
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(fullText) {
        displayedText = ""
        for (char in fullText) {
            displayedText += char
            delay(charDelayMillis)
        }
    }

    Text(text = displayedText, modifier = modifier)
}