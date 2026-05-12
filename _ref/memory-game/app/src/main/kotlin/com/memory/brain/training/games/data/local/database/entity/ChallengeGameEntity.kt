package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_games")
data class ChallengeGameEntity(
    @PrimaryKey
    val id: String,
    val gameId: String,
    val levelNumber: Int,
    val difficulty: Int,
    val requiredScore: Int,
    val timeLimit: Int,
    val isCompleted: Boolean,
    val stars: Int
)
