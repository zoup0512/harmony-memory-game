package com.memory.brain.training.games.domain.model

/**
 * Domain model representing a game
 */
data class GameInfo(
    val id: String,
    val nameResId: Int,
    val analyticsName: String,
    val thumbnailResId: Int,
    val unlockLevel: Int,
    val replayPrice: Int = 0,
    val hasLock: Boolean = false,
    val category: GameCategory
)

enum class GameCategory(
    val id: String,
    val nameResId: Int,
    val colorResId: Int,
    val backgroundColorResId: Int,
    val backgroundGridColorResId: Int
) {
    MEMORY("0", 0, 0, 0, 0),
    ATTENTION("1", 0, 0, 0, 0),
    SPEED("2", 0, 0, 0, 0),
    PROBLEM_SOLVING("3", 0, 0, 0, 0),
    FLEXIBILITY("4", 0, 0, 0, 0),
    IMAGINATION("5", 0, 0, 0, 0),
    PROMO("6", 0, 0, 0, 0)
}

/**
 * Trophy levels for each game
 */
data class TrophyLevel(
    val glass: Int,
    val bronze: Int,
    val silver: Int,
    val gold: Int
)

enum class TrophyType {
    GLASS,
    BRONZE,
    SILVER,
    GOLD
}
