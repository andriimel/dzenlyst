
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.am.dzenlyst.ui.screens.Stats.StatsScreenContent
import com.am.dzenlyst.ui.screens.Stats.StatsViewModel

@Composable
fun StatsScreen(viewModel: StatsViewModel = hiltViewModel()) {


    val sessions by viewModel.last7Sessions.collectAsState()


    val sessionCount = sessions.map { it.sessionCount }
    val total = sessionCount.sum()
    val completedTask by viewModel.completedTaskCount.collectAsState()



    StatsScreenContent(
        dailySessions = sessionCount ,
        focusSessions = total,
        completedTasks = completedTask,
        averageMinutesFocused = total * 25
    )
}



