package com.am.dzenlyst.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.am.dzenlyst.ui.navigation.Navigation

@Composable
fun DzenListAppUI(){
    MaterialTheme{
        Surface {
            Navigation()
        }
    }
}