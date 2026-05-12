package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.GameStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameStatsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: GameStatsEntity)
    
    @Update
    suspend fun updateStats(stats: GameStatsEntity)
    
    @Query("SELECT * FROM game_stats WHERE gameId = :gameId")
    fun getStats(gameId: String): Flow<GameStatsEntity?>
    
    @Query("SELECT * FROM game_stats")
    fun getAllStats(): Flow<List<GameStatsEntity>>
    
    @Query("DELETE FROM game_stats WHERE gameId = :gameId")
    suspend fun deleteStats(gameId: String)
}
