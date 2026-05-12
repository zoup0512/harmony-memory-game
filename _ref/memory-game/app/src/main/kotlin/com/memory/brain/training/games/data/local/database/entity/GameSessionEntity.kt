package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "game_sessions")
data class GameSessionEntity(
    @PrimaryKey
    val id: String,
    val gameId: String,
    val userId: String,
    val level: Int,
    val score: Int,
    val stars: Int,
    val coinsEarned: Int,
    val duration: Long,
    val isCompleted: Boolean,
    val timestamp: Date,
    val mode: String // SPRINT, WORKOUT, CHALLENGE, ONLINE
)
