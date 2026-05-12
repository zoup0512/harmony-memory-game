package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_games")
data class WorkoutGameEntity(
    @PrimaryKey
    val id: String,
    val gameId: String,
    val dayNumber: Int,
    val order: Int,
    val targetLevel: Int,
    val isCompleted: Boolean
)
