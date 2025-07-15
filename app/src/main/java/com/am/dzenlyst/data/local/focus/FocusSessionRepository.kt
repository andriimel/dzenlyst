package com.am.dzenlyst.data.local.focus

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class FocusSessionRepository @Inject constructor(
    private val dao: FocusSessionDao
) {

    fun getLast7Sessions():Flow<List<FocusSessionEntity>> = dao.getLast7Sessions()
    suspend fun getByDate(date: String): FocusSessionEntity? = dao.getByDate(date)

    suspend fun insert(session: FocusSessionEntity) = dao.insert(session)

    suspend fun update(session: FocusSessionEntity) = dao.update(session)
}