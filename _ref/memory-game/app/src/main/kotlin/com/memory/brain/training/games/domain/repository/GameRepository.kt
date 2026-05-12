package com.memory.brain.training.games.domain.repository

import com.memory.brain.training.games.domain.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for game-related operations
 */
interface GameRepository {
    
    // Game Info
    fun getAllGames(): Flow<List<GameInfo>>
    fun getGameById(gameId: String): Flow<GameInfo?>
    fun getGamesByCategory(category: GameCategory): Flow<List<GameInfo>>
    
    // Game Sessions
    suspend fun saveGameSession(session: GameSession)
    fun getGameSessions(gameId: String): Flow<List<GameSession>>
    fun getRecentSessions(limit: Int): Flow<List<GameSession>>
    fun getSessionsByMode(mode: GameMode): Flow<List<GameSession>>
    
    // Game Stats
    fun getGameStats(gameId: String): Flow<GameStats?>
    suspend fun updateGameStats(stats: GameStats)
    
    // Trophies
    fun getTrophyLevel(gameId: String): Flow<TrophyType?>
    fun getAllTrophies(): Flow<Map<String, TrophyType>>
    
    // Challenge
    fun getChallengeGames(): Flow<List<ChallengeGame>>
    fun getChallengeGame(id: String): Flow<ChallengeGame?>
    suspend fun updateChallengeGame(game: ChallengeGame)
    
    // Workout
    fun getWorkoutGames(dayNumber: Int): Flow<List<WorkoutGame>>
    fun getCurrentWorkoutDay(): Flow<Int>
    suspend fun updateWorkoutGame(game: WorkoutGame)
    suspend fun generateNewWorkoutDay()
}

/**
 * Game statistics
 */
data class GameStats(
    val gameId: String,
    val highestLevel: Int = 0,
    val totalPlays: Int = 0,
    val totalStars: Int = 0,
    val averageScore: Float = 0f,
    val bestScore: Int = 0,
    val lastPlayed: Long = 0
)
