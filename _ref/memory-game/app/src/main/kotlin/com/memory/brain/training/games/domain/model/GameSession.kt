package com.memory.brain.training.games.domain.model

import java.util.Date

/**
 * Domain model representing a game session
 */
data class GameSession(
    val id: String = "",
    val gameId: String,
    val userId: String,
    val level: Int,
    val score: Int,
    val stars: Int,
    val coinsEarned: Int,
    val duration: Long,
    val isCompleted: Boolean,
    val timestamp: Date = Date(),
    val mode: GameMode
)

enum class GameMode {
    SPRINT,
    WORKOUT,
    CHALLENGE,
    ONLINE
}

/**
 * Online game session with opponent info
 */
data class OnlineGameSession(
    val session: GameSession,
    val opponentId: String,
    val opponentName: String,
    val opponentScore: Int,
    val result: OnlineGameResult
)

enum class OnlineGameResult {
    WIN,
    LOSS,
    DRAW,
    PENDING
}

/**
 * Challenge game configuration
 */
data class ChallengeGame(
    val id: String,
    val gameId: String,
    val levelNumber: Int,
    val difficulty: Int,
    val requiredScore: Int,
    val timeLimit: Int,
    val isCompleted: Boolean = false,
    val stars: Int = 0
)

/**
 * Workout game configuration
 */
data class WorkoutGame(
    val id: String,
    val gameId: String,
    val dayNumber: Int,
    val order: Int,
    val targetLevel: Int,
    val isCompleted: Boolean = false
)
