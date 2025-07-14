import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroViewModel
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroPhase
import com.am.dzenlyst.ui.screens.Stats.StatsScreenContent

@Composable
fun StatsScreen() {
    val viewModel: PomodoroViewModel = hiltViewModel()


    val sessionCount by viewModel.completedWorkSession.collectAsState()

    val dailySessions = listOf(3, 2, 1, 4, 2, 5, 3)

    StatsScreenContent(
        dailySessions = dailySessions,
        focusSessions = sessionCount,
        completedTasks = 20,
        averageMinutesFocused = sessionCount * 25
    )
}



