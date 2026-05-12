package com.memory.brain.training.games.domain.model

/**
 * 游戏数据模型
 */
data class Game(
    val id: Int,
    val name: String,
    val description: String,
    val category: GameCategory,
    val iconResId: Int = 0,
    val isLocked: Boolean = false,
    val requiredStars: Int = 0
)
