package com.am.dzenlyst.ui.screens.PomodoScreen

import androidx.compose.foundation.Image
import com.am.dzenlyst.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

@Composable
fun FocusHeader(
    onModeClick: ()-> Unit,
    onInfoClick:()-> Unit
)  {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton (onClick = onModeClick,
            modifier = Modifier
                .size(72.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_change_mode),
                contentDescription = "change mode icon",
                modifier = Modifier.size(36.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.focusBlueLight))
            )
        }
        IconButton(
            onClick = onInfoClick,
            modifier = Modifier
                .size(72.dp)
        ) {
           Image(
               painter = painterResource(id = R.drawable.baseline_info_outline_24),
               contentDescription = "info mode",
               modifier = Modifier.size(36.dp),
               colorFilter = ColorFilter.tint(colorResource(id = R.color.focusBlueLight))
           )
        }
    }
}