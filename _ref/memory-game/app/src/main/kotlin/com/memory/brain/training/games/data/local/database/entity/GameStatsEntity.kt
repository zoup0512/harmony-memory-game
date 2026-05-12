package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_stats")
data class GameStatsEntity(
    @PrimaryKey
    val gameId: String,
    val highestLevel: Int,
    val totalPlays: Int,
    val totalStars: Int,
    val averageScore: Float,
    val bestScore: Int,
    val lastPlayed: Long
)
