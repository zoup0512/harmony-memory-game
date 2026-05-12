package com.memory.brain.training.games.domain.model

/**
 * Domain model representing a user
 */
data class User(
    val id: String = "",
    val nickname: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val coins: Int = 0,
    val stars: Int = 0,
    val onlineRank: Int = 0,
    val isPro: Boolean = false,
    val adsRemoved: Boolean = false,
    val unlimitedOnline: Boolean = false,
    val secretGameUnlocked: Boolean = false,
    val allGamesUnlocked: Boolean = false
)

/**
 * User statistics
 */
data class UserStats(
    val totalGamesPlayed: Int = 0,
    val totalStarsEarned: Int = 0,
    val totalCoinsEarned: Int = 0,
    val highestLevel: Int = 0,
    val trophiesEarned: Int = 0,
    val onlineWins: Int = 0,
    val onlineLosses: Int = 0,
    val onlineDraws: Int = 0
)
