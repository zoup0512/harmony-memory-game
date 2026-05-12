package com.memory.brain.training.games.domain.repository

import com.memory.brain.training.games.domain.model.OnlineGameSession
import com.memory.brain.training.games.domain.model.OnlineGameResult
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for online game operations
 */
interface OnlineRepository {
    
    // Matchmaking
    suspend fun searchForMatch(gameId: String): Result<String> // Returns match ID
    suspend fun cancelSearch()
    fun getMatchStatus(): Flow<MatchStatus>
    
    // Game Session
    suspend fun confirmMatch(matchId: String)
    suspend fun startOnlineGame(matchId: String)
    suspend fun submitScore(matchId: String, score: Int)
    suspend fun submitIntermediateResults(matchId: String, results: List<Int>)
    fun getOpponentIntermediateResults(matchId: String): Flow<List<Int>?>
    fun getGameResult(matchId: String): Flow<OnlineGameResult?>
    
    // History
    fun getOnlineGameHistory(): Flow<List<OnlineGameSession>>
    fun getOnlineStats(): Flow<OnlineStats>
    
    // Leaderboard
    fun getGlobalLeaderboard(limit: Int): Flow<List<LeaderboardEntry>>
    fun getUserRank(): Flow<Int>
}

/**
 * Match status during online game
 */
sealed class MatchStatus {
    object Idle : MatchStatus()
    object Searching : MatchStatus()
    data class MatchFound(val matchId: String, val opponentName: String) : MatchStatus()
    data class Confirmed(val matchId: String) : MatchStatus()
    data class InProgress(val matchId: String) : MatchStatus()
    data class Finished(val matchId: String, val result: OnlineGameResult) : MatchStatus()
    data class Error(val message: String) : MatchStatus()
}

/**
 * Online game statistics
 */
data class OnlineStats(
    val totalGames: Int = 0,
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0,
    val currentRank: Int = 0,
    val highestRank: Int = 0
)

/**
 * Leaderboard entry
 */
data class LeaderboardEntry(
    val userId: String,
    val nickname: String,
    val photoUrl: String,
    val rank: Int,
    val score: Int,
    val wins: Int
)
