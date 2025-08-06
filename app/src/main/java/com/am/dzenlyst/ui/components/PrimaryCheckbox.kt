package com.am.dzenlyst.ui.components

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.getValue
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


@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Int = 24,
    checkedColor: Color = Color(0xFF4CAF50),
    uncheckedColor: Color = Color.White,
    borderColor: Color = Color.Gray
) {
    val backgroundColor by animateColorAsState(if (checked) checkedColor else uncheckedColor)
    val currentBorderColor by animateColorAsState(if (checked) checkedColor else borderColor)

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .border(width = 2.dp, color = currentBorderColor, shape = RoundedCornerShape(6.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Checked",
                tint = Color.White,
                modifier = Modifier.size((size * 0.7).dp)
            )
        }
    }
}