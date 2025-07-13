package com.am.dzenlyst.ui.screens.PomodoScreen


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavBar(
    selected: String = "focus", // or pass current route later
    onItemSelected: (String) -> Unit = {}
) {
    val items = listOf(
        BottomNavItem("focus", "Focus", Icons.Default.Notifications),
        BottomNavItem("tasks", "Tasks", Icons.Default.List),
        BottomNavItem("stats", "Stats", Icons.Default.Info),
        BottomNavItem("coach", "Coach", Icons.Default.Person),
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.route == selected,
                onClick = { onItemSelected(item.route) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)