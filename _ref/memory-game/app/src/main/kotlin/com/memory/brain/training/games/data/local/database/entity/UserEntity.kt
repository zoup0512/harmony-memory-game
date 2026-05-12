package com.memory.brain.training.games.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val nickname: String,
    val email: String,
    val photoUrl: String,
    val coins: Int,
    val stars: Int,
    val onlineRank: Int,
    val isPro: Boolean,
    val adsRemoved: Boolean,
    val unlimitedOnline: Boolean,
    val secretGameUnlocked: Boolean,
    val allGamesUnlocked: Boolean,
    val soundEnabled: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
