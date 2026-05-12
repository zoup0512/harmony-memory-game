package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.GameSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSessionDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: GameSessionEntity)
    
    @Query("SELECT * FROM game_sessions WHERE gameId = :gameId ORDER BY timestamp DESC")
    fun getSessionsByGame(gameId: String): Flow<List<GameSessionEntity>>
    
    @Query("SELECT * FROM game_sessions ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentSessions(limit: Int): Flow<List<GameSessionEntity>>
    
    @Query("SELECT * FROM game_sessions WHERE mode = :mode ORDER BY timestamp DESC")
    fun getSessionsByMode(mode: String): Flow<List<GameSessionEntity>>
    
    @Query("SELECT * FROM game_sessions WHERE userId = :userId ORDER BY timestamp DESC")
    fun getUserSessions(userId: String): Flow<List<GameSessionEntity>>
    
    @Query("DELETE FROM game_sessions WHERE id = :sessionId")
    suspend fun deleteSession(sessionId: String)
}
