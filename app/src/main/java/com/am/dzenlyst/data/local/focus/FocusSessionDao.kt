package com.am.dzenlyst.data.local.focus

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FocusSessionDao {

    @Query ("SELECT * FROM focus_sessions ORDER BY date DESC LIMIT 7")
    fun getLast7Sessions(): Flow<List<FocusSessionEntity>>

    @Query ("SELECT * FROM focus_sessions WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: String): FocusSessionEntity?

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: FocusSessionEntity)

    @Update
    suspend fun update(session: FocusSessionEntity)
}