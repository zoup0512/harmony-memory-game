package com.memory.brain.training.games.data.local.database.dao

import androidx.room.*
import com.memory.brain.training.games.data.local.database.entity.ChallengeGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeGameDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengeGame(game: ChallengeGameEntity)
    
    @Update
    suspend fun updateChallengeGame(game: ChallengeGameEntity)
    
    @Query("SELECT * FROM challenge_games ORDER BY levelNumber ASC")
    fun getAllChallengeGames(): Flow<List<ChallengeGameEntity>>
    
    @Query("SELECT * FROM challenge_games WHERE id = :id")
    fun getChallengeGame(id: String): Flow<ChallengeGameEntity?>
    
    @Query("SELECT * FROM challenge_games WHERE gameId = :gameId ORDER BY levelNumber ASC")
    fun getChallengeGamesByGameId(gameId: String): Flow<List<ChallengeGameEntity>>
}
