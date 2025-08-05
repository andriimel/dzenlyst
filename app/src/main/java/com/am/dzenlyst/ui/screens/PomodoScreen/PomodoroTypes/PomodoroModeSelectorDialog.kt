package com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes


import android.util.Log
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.R
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroViewModel
import com.am.dzenlyst.ui.utils.MontserratFont

@Composable
fun PomodoroModeSelectorDialog(
    currentMode: PomodoroMode,
    onSelect: (PomodoroMode) -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select Focus Mode",
                    fontFamily = MontserratFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.regularTextColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                ModeGrid(
                    currentMode = currentMode,
                    onSelect = {mode ->

                        onSelect(mode)
                        onDismiss()

                        Log.d("Selected:","$mode mode")
                    }
                )
            }
        },
        confirmButton = {}
    )
}


