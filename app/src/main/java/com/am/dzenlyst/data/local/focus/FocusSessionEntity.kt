package com.am.dzenlyst.data.local.focus

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "focus_sessions")

data class FocusSessionEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val sessionCount : Int
)