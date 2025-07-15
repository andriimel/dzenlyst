package com.am.dzenlyst.data.local.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface TaskDao {

    @Query(
        """
    SELECT * FROM tasks 
    ORDER BY 
        CASE 
            WHEN priority = 'High' THEN 1
            WHEN priority = 'Normal' THEN 2
            WHEN priority = 'Low' THEN 3
            ELSE 4
        END,
        id DESC
    """
    )
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query ("SELECT  COUNT(*) FROM tasks WHERE isDone = 1 ")
    fun getCompletedTask():Flow<Int>

    @Query("""SELECT * FROM tasks WHERE isDone = 0 
        ORDER BY 
        CASE
        WHEN priority = 'High' THEN 1
            WHEN priority = 'Normal' THEN 2
            WHEN priority = 'Low' THEN 3
            ELSE 4
        END
        LIMIT 3
    """)
    fun getTop3UndoneTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}