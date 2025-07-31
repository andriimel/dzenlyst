package com.am.dzenlyst.ui.components

import com.am.dzenlyst.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color  // імпорт Comopse Color

@Composable
fun PrimaryCheckbox(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier

            .clip(RoundedCornerShape(2.dp))
            .background(
                color = if (checked)
                    colorResource(id = R.color.checkboxCheckColor)
                else
                    colorResource(id = R.color.checkboxUncheckColor)
            )
            .border(
                width = 1.dp,
                color = if (checked)
                    colorResource(id = R.color.checkboxCheckColor)
                else
                    Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable { onCheckedChange() },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = colorResource(id = R.color.checkmarkColor),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
