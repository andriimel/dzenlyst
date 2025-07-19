package com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes

data class PomodoroMode(
    val type: PomodoroModeType,
    val workDuration: Int,
    val shortBreak: Int,
    val longBreak: Int,
    val roundsBeforeLongBreak: Int
)


val PomodoroModes = listOf(
    PomodoroMode(PomodoroModeType.CLASSIC, 25, 5, 15, 4),
    PomodoroMode(PomodoroModeType.EXTENDED, 50, 10, 20, 4),
    PomodoroMode(PomodoroModeType.SPRINT, 15, 3, 10, 4),
    PomodoroMode(PomodoroModeType.BALANCED, 35, 7, 15, 4)
)
fun getPomodoroModeByType(typeName: String?): PomodoroMode {
    val type = typeName?.let {
        try {
            PomodoroModeType.valueOf(it)
        } catch (e: IllegalArgumentException) {
            PomodoroModeType.CLASSIC
        }
    } ?: PomodoroModeType.CLASSIC

    return PomodoroModes.firstOrNull { it.type == type } ?: PomodoroModes.first()
}